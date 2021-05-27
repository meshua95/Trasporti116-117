/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.trasporto;

import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicDigitalTwinMetadata;
import com.azure.digitaltwins.core.BasicRelationship;
import digitalTwins.Client;
import model.Route;
import model.TransportState;
import utils.Constants;

import java.time.LocalDateTime;
import java.util.List;

public class TransportDigitalTwin {

    public static void createTrasporto(String dtId, LocalDateTime dataOra, TransportState stato, Route route, String ambulanzaId, String pazienteId, String operatoreId){
        //create digital twin "trasporto"
        BasicDigitalTwin trasportoDT = new BasicDigitalTwin(dtId)
                .setMetadata(
                        new BasicDigitalTwinMetadata().setModelId(Constants.TRASPORTO_ID)
                )
                .addToContents("dataOra", dataOra)
                .addToContents("itinerario", route)
                .addToContents("stato", stato.getValue());

        BasicDigitalTwin basicTwinResponse = Client.getClient().createOrReplaceDigitalTwin(dtId, trasportoDT, BasicDigitalTwin.class);
        System.out.println(basicTwinResponse.getId());

        //add relationship whit ambulanza
        createTrasportoRelationship(dtId, ambulanzaId, "usa");

        //add relationship whit paziente
        createTrasportoRelationship(dtId, pazienteId, "trasporta");

        //add relationship whit operatore
        createTrasportoRelationship(dtId, operatoreId, "guidata");
    }

    private static void createTrasportoRelationship(String trasportoId, String targetId, String relationshipName){
        BasicRelationship trasportoToTargetRelationship =
                new BasicRelationship(
                        trasportoId + "to" + targetId,
                        trasportoId,
                        targetId,
                        relationshipName);

        BasicRelationship createdRelationship = Client.getClient().createOrReplaceRelationship(
                trasportoId,
                trasportoId + "to" + targetId,
                trasportoToTargetRelationship,
                BasicRelationship.class);
    }

    public static void deleteTrasporto(String idTrasporto) {
            Client.getClient().listRelationships(idTrasporto, BasicRelationship.class)
                    .forEach(rel -> Client.getClient().deleteRelationship(idTrasporto, rel.getId()));
            Client.getClient().deleteDigitalTwin(idTrasporto);
    }

    public static void deleteAllTrasporto(List<String> dtId) {
        dtId.forEach(TransportDigitalTwin::deleteTrasporto);
    }
}
