/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.ambulanza;

import com.azure.core.http.rest.PagedIterable;
import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicDigitalTwinMetadata;
import com.azure.digitaltwins.core.BasicRelationship;
import digitalTwins.Client;
import model.AmbulanceId;
import domain.trasporto.TransportDigitalTwin;
import model.AmbulanceState;
import utils.Constants;
import utils.errorCode.DeleteAmbulanceStatusCode;

import java.util.ArrayList;

public class AmbulanceDigitalTwin {

    public static void createAmbulance(AmbulanceState stato, int numeroAmbulanza){
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

    public static DeleteAmbulanceStatusCode deleteAmbulance(AmbulanceId dtId) {
        if(!TransportDigitalTwin.getTransportOfAmbulance(dtId).isEmpty()){
            return DeleteAmbulanceStatusCode.TRANSPORT_RELATION_EXISTING;
        }
        Client.getClient().listRelationships(dtId.toString(), BasicRelationship.class)
                .forEach(rel -> Client.getClient().deleteRelationship(dtId.toString(), rel.getId()));
        Client.getClient().deleteDigitalTwin(dtId.toString());

        return DeleteAmbulanceStatusCode.DELETED;
    }

    public static ArrayList<AmbulanceId> getAllAmbulanceIdTwins(){
        ArrayList<AmbulanceId> ambulanzeIds = new ArrayList<>();
            String query = "SELECT $dtId FROM DIGITALTWINS WHERE IS_OF_MODEL('"+ Constants.AMBULANZA_MODEL_ID + "')";
        PagedIterable<BasicDigitalTwin> pageableResponse = Client.getClient().query(query, BasicDigitalTwin.class);
        pageableResponse.forEach(r-> ambulanzeIds.add(new AmbulanceId(r.getId())));
        return ambulanzeIds;
    }
}
