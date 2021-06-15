/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package digitalTwins.ambulance;

import com.azure.core.http.rest.PagedIterable;
import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicDigitalTwinMetadata;
import com.azure.digitaltwins.core.BasicRelationship;
import com.azure.digitaltwins.core.implementation.models.ErrorResponseException;
import digitalTwins.Client;
import domain.ambulanceBoundedContext.AmbulanceId;
import utils.AzureErrorMessage;
import domain.ambulanceBoundedContext.Coordinates;
import utils.Constants;
import utils.errorCode.DeleteDigitalTwinStatusCode;
import org.json.simple.JSONObject;
import java.util.ArrayList;

public class AmbulanceDigitalTwin {

    public static String createAmbulance(int numeroAmbulanza){
        AmbulanceId ambulanceId = new AmbulanceId(numeroAmbulanza);

        BasicDigitalTwin ambulanzaDT = new BasicDigitalTwin(ambulanceId.getAmbulanceId())
                .setMetadata(
                        new BasicDigitalTwinMetadata().setModelId(Constants.AMBULANCE_MODEL_ID)
                )
                .addToContents("number", numeroAmbulanza);

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

        BasicRelationship createdRelationship = Client.getClient().createOrReplaceRelationship(
                ambulanceId.getAmbulanceId(),
                ambulanceId.getAmbulanceId() + "to" + ambulanceId.getGpsId(),
                new BasicRelationship(
                        ambulanceId.getAmbulanceId() + "to" + ambulanceId.getGpsId(),
                        ambulanceId.getAmbulanceId(),
                        ambulanceId.getGpsId(),
                        "contains"),
                BasicRelationship.class);

        System.out.println(createdRelationship.getId());

        return basicTwinResponse.getId();
    }

    public static void createAmbulance(AmbulanceId ambulanceId){
        int ambulanceNumber = ambulanceId.getAmbulanceNumber();
        createAmbulance(ambulanceNumber);
    }

    public static DeleteDigitalTwinStatusCode deleteAmbulance(AmbulanceId ambulanceId) {
        try{
            Client.getClient().listRelationships(ambulanceId.getAmbulanceId(), BasicRelationship.class)
                    .forEach(rel -> Client.getClient().deleteRelationship(ambulanceId.getAmbulanceId(), rel.getId()));
            Client.getClient().deleteDigitalTwin(ambulanceId.getAmbulanceId());
            Client.getClient().deleteDigitalTwin(ambulanceId.getGpsId());

            return DeleteDigitalTwinStatusCode.DELETED;
        } catch(ErrorResponseException e){
            if(e.getLocalizedMessage().contains(AzureErrorMessage.RELATIONSHIP_NOT_DELETED)) {
                return DeleteDigitalTwinStatusCode.TRANSPORT_RELATION_EXISTING;
            }
            throw e;
        }
    }

    public static ArrayList<AmbulanceId> getAllAmbulanceIdTwins(){
        ArrayList<AmbulanceId> ambulanzeIds = new ArrayList<>();
            String query = "SELECT $dtId FROM DIGITALTWINS WHERE IS_OF_MODEL('"+ Constants.AMBULANCE_MODEL_ID + "')";
        PagedIterable<BasicDigitalTwin> pageableResponse = Client.getClient().query(query, BasicDigitalTwin.class);
        pageableResponse.forEach(r-> ambulanzeIds.add(new AmbulanceId(r.getId())));
        return ambulanzeIds;
    }

    public static Coordinates getGPSCoordinatesOfAmbulance(AmbulanceId ambulanceId){
        final double longitude;
        final double latitude;

        String query= "SELECT * FROM digitaltwins  WHERE $dtId='"+ambulanceId.getGpsId()+"'";
        PagedIterable<JSONObject> pageableResponse = Client.getClient().query(query, JSONObject.class);

        latitude = pageableResponse.stream().mapToDouble(a-> Double.parseDouble(a.get("latitude").toString())).findFirst().getAsDouble();
        longitude = pageableResponse.stream().mapToDouble(a-> Double.parseDouble(a.get("longitude").toString())).findFirst().getAsDouble();

        return new Coordinates(longitude, latitude);
    }
}
