/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package digitalTwinsAPI;

import com.azure.core.http.rest.PagedIterable;
import com.azure.digitaltwins.core.BasicDigitalTwin;
import domain.transport.ambulance.AmbulanceId;
import domain.transport.TransportId;
import org.json.simple.JSONObject;
import utils.Constants;

import java.util.ArrayList;

public class GetTransport {
    /**
     * Get all transport digital twin
     *
     * @return list of all transport
     */
    public static ArrayList<TransportId> getAllTransportId(){
        ArrayList<TransportId> transoprtIds = new ArrayList<>();
        String query = "SELECT $dtId FROM DIGITALTWINS WHERE IS_OF_MODEL('"+ Constants.TRANSPORT_MODEL_ID + "')";
        PagedIterable<BasicDigitalTwin> pageableResponse = Client.getClient().query(query, BasicDigitalTwin.class);
        pageableResponse.forEach(r-> transoprtIds.add(new TransportId(r.getId())));
        return transoprtIds;
    }

    /**
     * Get all transport related to an ambulance
     *
     * @param id of ambulance
     * @return list of TransportId
     */
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

    /**
     * Get all transport in progress
     *
     * @return list of TransportId in progress
     */
    public static ArrayList<TransportId> getAllTransportInProgress(){
        ArrayList<TransportId> transportIds = new ArrayList<>();
        String query = "SELECT * " +
                "FROM DIGITALTWINS " +
                "WHERE IS_OF_MODEL('" + Constants.TRANSPORT_MODEL_ID + "') AND NOT IS_DEFINED ( endDateTime )";

        Client.getClient().query(query, BasicDigitalTwin.class)
                .forEach(r-> transportIds.add(new TransportId(r.getId())));
        return transportIds;
    }
}
