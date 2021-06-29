/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */
package digitalTwinsAPI;

import com.azure.core.http.rest.PagedIterable;
import domain.transport.ambulance.AmbulanceId;
import domain.transport.ambulance.Coordinates;
import org.json.simple.JSONObject;
import utils.WaitForClientResponse;
import utils.errorCode.QueryTimeOutException;

public class GetGPSCoordinates {
    private GetGPSCoordinates(){}

    /**
     * Get the GPS coordinates of a ambulance
     *
     * @param ambulanceId
     * @return coordinates of a ambulance
     */
    public static Coordinates getGPSCoordinatesOfAmbulance(AmbulanceId ambulanceId) throws QueryTimeOutException {
        final double longitude;
        final double latitude;

        String query= "SELECT * FROM digitaltwins  WHERE $dtId='"+ambulanceId.getGpsId()+"'";
        PagedIterable<JSONObject> pageableResponse = Client.getClient().query(query, JSONObject.class);

        WaitForClientResponse.waitForClientResponse(pageableResponse);
        latitude = pageableResponse.stream().mapToDouble(a-> Double.parseDouble(a.get("latitude").toString())).findFirst().getAsDouble();
        longitude = pageableResponse.stream().mapToDouble(a-> Double.parseDouble(a.get("longitude").toString())).findFirst().getAsDouble();

        return new Coordinates(longitude, latitude);
    }
}
