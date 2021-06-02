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

    public static TransportId createTransport(LocalDateTime dateTime, TransportState state, Route route, AmbulanceId ambulanceId, FiscalCode patientId, OperatorId operatorId){
        TransportId transportId = generateTransportId(patientId, dateTime);
        System.out.println(transportId);

        //create digital twin "trasporto"
        BasicDigitalTwin transportDT = new BasicDigitalTwin(transportId.getId())
                .setMetadata(
                        new BasicDigitalTwinMetadata().setModelId(Constants.TRASPORTO_ID)
                )
                .addToContents("dateTime", dateTime)
                .addToContents("route", route)
                .addToContents("state", state.getValue());

        BasicDigitalTwin basicTwinResponse = Client.getClient().createOrReplaceDigitalTwin(transportId.getId(), transportDT, BasicDigitalTwin.class);
        System.out.println(basicTwinResponse.getId());

        //add relationship whit ambulanza
        createTrasportoRelationship(transportId, ambulanceId.getAmbulanceId(), "use");

        //add relationship whit paziente
        createTrasportoRelationship(transportId, patientId.getFiscalCode(), "transport");

        //add relationship whit operatore
        createTrasportoRelationship(transportId, operatorId.getOperatorId(), "drive");

        return transportId;
    }

    private static void createTrasportoRelationship(TransportId transportId, String targetId, String relationshipName){
        BasicRelationship trasportoToTargetRelationship =
                new BasicRelationship(
                        transportId.getId() + "to" + targetId,
                        transportId.getId(),
                        targetId,
                        relationshipName);

        System.out.println(targetId);
        BasicRelationship createdRelationship = Client.getClient().createOrReplaceRelationship(
                transportId.getId(),
                transportId.getId() + "to" + targetId,
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
        ArrayList<TransportId> transportIds = new ArrayList<>();
        String query = "SELECT source " +
                "FROM DIGITALTWINS source " +
                "JOIN target RELATED source.use " +
                "WHERE target.$dtId = '"+ id.getAmbulanceId() +"'";
        PagedIterable<BasicDigitalTwin> pageableResponse = Client.getClient().query(query, BasicDigitalTwin.class);
        pageableResponse.forEach(r-> transportIds.add(new TransportId(r.getId())));
        return transportIds;
    }

    public static TransportId generateTransportId(FiscalCode patientId, LocalDateTime dataOra){
        return new TransportId(dataOra.toLocalDate() + "_" + dataOra.getHour() + "-" + dataOra.getMinute() + "_" + patientId.getFiscalCode());
    }
}
