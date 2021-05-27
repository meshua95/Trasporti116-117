/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.ambulanza;

import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicDigitalTwinMetadata;
import com.azure.digitaltwins.core.BasicRelationship;
import digitalTwins.Client;
import model.StatoAmbulanza;
import utils.Constants;

import java.util.List;

public class AmbulanzaDigitalTwin {

    public static void createAmbulanza(StatoAmbulanza stato, int numeroAmbulanza){
        String ambulanzaId = "ambulanza"+numeroAmbulanza;
        String GSPId = "GPS"+numeroAmbulanza;
        BasicDigitalTwin ambulanzaDT = new BasicDigitalTwin(ambulanzaId)
                .setMetadata(
                        new BasicDigitalTwinMetadata().setModelId(Constants.AMBULANZA_MODEL_ID)
                )
                .addToContents("number", numeroAmbulanza)
                .addToContents("stato", stato.getValue());
        BasicDigitalTwin GPSdt = new BasicDigitalTwin(GSPId)
                .setMetadata(
                        new BasicDigitalTwinMetadata().setModelId(Constants.GPS_MODEL_ID)
                )
                .addToContents("longitudine", 0)
                .addToContents("latitudine", 0);

        BasicDigitalTwin basicTwinResponse = Client.getClient().createOrReplaceDigitalTwin(ambulanzaId, ambulanzaDT, BasicDigitalTwin.class);
        BasicDigitalTwin basicTwinResponseGPS = Client.getClient().createOrReplaceDigitalTwin(GSPId, GPSdt, BasicDigitalTwin.class);

        System.out.println(basicTwinResponse.getId());
        System.out.println(basicTwinResponseGPS.getId());

        BasicRelationship rel =
                new BasicRelationship(
                        ambulanzaId + "to" + GSPId,
                        ambulanzaId,
                        GSPId,
                        "contiene");

        BasicRelationship createdRelationship = Client.getClient().createOrReplaceRelationship(
                ambulanzaId,
                ambulanzaId + "to" + GSPId,
                rel,
                BasicRelationship.class);
        System.out.println(createdRelationship.getId());

    }

    public static void deleteAmbulanza(List<String> dtId) {
        dtId.forEach(id -> {
            Client.getClient().listRelationships(id, BasicRelationship.class)
                    .forEach(rel -> Client.getClient().deleteRelationship(id, rel.getId()));
            Client.getClient().deleteDigitalTwin(id);
        });
    }
}
