/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */
package digitalTwinsAPI;

import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicDigitalTwinMetadata;
import com.azure.digitaltwins.core.BasicRelationship;
import domain.transport.ambulance.AmbulanceId;
import utils.Constants;

public class CreateAmbulance {
    /**
     * Create an ambulance digital twin by number
     *
     * @param ambulanceNumber number of the ambulance to be created
     * @return id of the ambulance created
     */
    public static String createAmbulance(int ambulanceNumber){
        AmbulanceId ambulanceId = new AmbulanceId(ambulanceNumber);

        BasicDigitalTwin ambulanzaDT = new BasicDigitalTwin(ambulanceId.getAmbulanceId())
                .setMetadata(
                        new BasicDigitalTwinMetadata().setModelId(Constants.AMBULANCE_MODEL_ID)
                )
                .addToContents("number", ambulanceNumber);

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
    /**
     * Create an ambulance digital twin by id
     *
     * @param ambulanceId id of the ambulance to be created
     * @return void
     */
    public static void createAmbulance(AmbulanceId ambulanceId){
        int ambulanceNumber = ambulanceId.getAmbulanceNumber();
        createAmbulance(ambulanceNumber);
    }
}