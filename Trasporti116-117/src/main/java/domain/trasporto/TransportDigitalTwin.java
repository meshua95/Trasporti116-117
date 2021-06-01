/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.trasporto;

import com.azure.core.http.rest.PagedIterable;
import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicDigitalTwinMetadata;
import com.azure.digitaltwins.core.BasicRelationship;
import digitalTwins.Client;
import model.*;
import model.Route;
import model.TransportState;
import utils.Constants;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransportDigitalTwin {

    public static void createTransport(LocalDateTime dateTime, TransportState state, Route route, AmbulanceId ambulanceId, FiscalCode patientId, OperatorId operatorId){
        TransportId transportId = generateTransportId(patientId, dateTime);
        //create digital twin "trasporto"
        BasicDigitalTwin trasportoDT = new BasicDigitalTwin(transportId.getId())
                .setMetadata(
                        new BasicDigitalTwinMetadata().setModelId(Constants.TRASPORTO_ID)
                )
                .addToContents("dataOra", dateTime)
                .addToContents("itinerario", route)
                .addToContents("stato", state.getValue());

        BasicDigitalTwin basicTwinResponse = Client.getClient().createOrReplaceDigitalTwin(transportId.getId(), trasportoDT, BasicDigitalTwin.class);
        System.out.println(basicTwinResponse.getId());

        //add relationship whit ambulanza
        createTrasportoRelationship(transportId, ambulanceId.getAmbulanceId(), "usa");

        //add relationship whit paziente
        createTrasportoRelationship(transportId, patientId.getFiscalCode(), "trasporta");

        //add relationship whit operatore
        createTrasportoRelationship(transportId, operatorId.getOperatorId(), "guidata");
    }

    private static void createTrasportoRelationship(TransportId transportId, String targetId, String relationshipName){
        BasicRelationship trasportoToTargetRelationship =
                new BasicRelationship(
                        transportId + "to" + targetId,
                        transportId.getId(),
                        targetId,
                        relationshipName);

        BasicRelationship createdRelationship = Client.getClient().createOrReplaceRelationship(
                transportId.getId(),
                transportId + "to" + targetId,
                trasportoToTargetRelationship,
                BasicRelationship.class);
    }

    public static void deleteTransport(TransportId transportId) {
            Client.getClient().listRelationships(transportId.getId(), BasicRelationship.class)
                    .forEach(rel -> Client.getClient().deleteRelationship(transportId.getId(), rel.getId()));
            Client.getClient().deleteDigitalTwin(transportId.getId());
    }

    public static void deleteAllTransport(List<TransportId> dtId) {
        dtId.forEach(TransportDigitalTwin::deleteTransport);
    }

    public static ArrayList<TransportId> getAllTransportId(){
        ArrayList<TransportId> transoprtIds = new ArrayList<>();
        String query = "SELECT $dtId FROM DIGITALTWINS WHERE WHERE IS_OF_MODEL('"+ Constants.TRASPORTO_ID + "')";
        PagedIterable<BasicDigitalTwin> pageableResponse = Client.getClient().query(query, BasicDigitalTwin.class);
        pageableResponse.forEach(r-> transoprtIds.add(new TransportId(r.getId())));
        return transoprtIds;
    }

    public static ArrayList<TransportId> getTransportOfAmbulance(AmbulanceId id){
        ArrayList<TransportId> transoprtIds = new ArrayList<>();
        String query = "SELECT source " +
                "FROM DIGITALTWINS source " +
                "JOIN target RELATED source.usa " +
                "WHERE target.$dtId = '"+ id.getAmbulanceId() +"'";
        PagedIterable<BasicDigitalTwin> pageableResponse = Client.getClient().query(query, BasicDigitalTwin.class);
        pageableResponse.forEach(r-> transoprtIds.add(new TransportId(r.getId())));
        return transoprtIds;
    }

    private static TransportId generateTransportId(FiscalCode patientId, LocalDateTime dataOra){
        return new TransportId(patientId.getFiscalCode() +"-"
                + dataOra.toString());
    }
}
