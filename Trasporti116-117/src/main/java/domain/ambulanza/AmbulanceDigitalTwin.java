/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.ambulanza;

import com.azure.core.http.rest.PagedIterable;
import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicDigitalTwinMetadata;
import com.azure.digitaltwins.core.BasicRelationship;
import com.azure.digitaltwins.core.implementation.models.ErrorResponseException;
import digitalTwins.Client;
import model.AmbulanceId;
import model.AmbulanceState;
import utils.AzureErrorMessage;
import utils.Constants;
import utils.errorCode.DeleteAmbulanceStatusCode;

import java.util.ArrayList;

public class AmbulanceDigitalTwin {

    public static void createAmbulance(AmbulanceState stato, int numeroAmbulanza){
        AmbulanceId ambulanceId = new AmbulanceId(numeroAmbulanza);

        BasicDigitalTwin ambulanzaDT = new BasicDigitalTwin(ambulanceId.getAmbulanceId())
                .setMetadata(
                        new BasicDigitalTwinMetadata().setModelId(Constants.AMBULANZA_MODEL_ID)
                )
                .addToContents("number", numeroAmbulanza)
                .addToContents("state", stato.getValue());
        BasicDigitalTwin GPSdt = new BasicDigitalTwin(ambulanceId.getGpsId())
                .setMetadata(
                        new BasicDigitalTwinMetadata().setModelId(Constants.GPS_MODEL_ID)
                )
                .addToContents("longitude", 0)
                .addToContents("latitude", 0);

        BasicDigitalTwin basicTwinResponse = Client.getClient().createOrReplaceDigitalTwin(ambulanceId.getAmbulanceId(), ambulanzaDT, BasicDigitalTwin.class);
        BasicDigitalTwin basicTwinResponseGPS = Client.getClient().createOrReplaceDigitalTwin(ambulanceId.getGpsId(), GPSdt, BasicDigitalTwin.class);

        System.out.println(basicTwinResponse.getId());
        System.out.println(basicTwinResponseGPS.getId());

        BasicRelationship rel =
                new BasicRelationship(
                        ambulanceId.getAmbulanceId() + "to" + ambulanceId.getGpsId(),
                        ambulanceId.getAmbulanceId(),
                        ambulanceId.getGpsId(),
                        "contains");

        BasicRelationship createdRelationship = Client.getClient().createOrReplaceRelationship(
                ambulanceId.getAmbulanceId(),
                ambulanceId.getAmbulanceId() + "to" + ambulanceId.getGpsId(),
                rel,
                BasicRelationship.class);
        System.out.println(createdRelationship.getId());
    }

    public static void createAmbulance(AmbulanceState state, AmbulanceId ambulanceId){
        int ambulanceNumber = ambulanceId.getAmbulanceNumber();
        createAmbulance(state, ambulanceNumber);
    }

    public static DeleteAmbulanceStatusCode deleteAmbulance(AmbulanceId ambulanceId) {
        try{
            Client.getClient().listRelationships(ambulanceId.getAmbulanceId(), BasicRelationship.class)
                    .forEach(rel -> Client.getClient().deleteRelationship(ambulanceId.getAmbulanceId(), rel.getId()));
            Client.getClient().deleteDigitalTwin(ambulanceId.getAmbulanceId());
            Client.getClient().deleteDigitalTwin(ambulanceId.getGpsId());

            return DeleteAmbulanceStatusCode.DELETED;
        } catch(ErrorResponseException e){
            if(e.getLocalizedMessage().contains(AzureErrorMessage.RELATIONSHIP_NOT_DELETED)) {
                return DeleteAmbulanceStatusCode.TRANSPORT_RELATION_EXISTING;
            }
            throw e;
        }
    }

    public static ArrayList<AmbulanceId> getAllAmbulanceIdTwins(){
        ArrayList<AmbulanceId> ambulanzeIds = new ArrayList<>();
            String query = "SELECT $dtId FROM DIGITALTWINS WHERE IS_OF_MODEL('"+ Constants.AMBULANZA_MODEL_ID + "')";
        PagedIterable<BasicDigitalTwin> pageableResponse = Client.getClient().query(query, BasicDigitalTwin.class);
        pageableResponse.forEach(r-> ambulanzeIds.add(new AmbulanceId(r.getId())));
        return ambulanzeIds;
    }
}
