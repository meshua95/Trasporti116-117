/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */
package digitalTwinsAPI;

import com.azure.core.http.rest.PagedIterable;
import com.azure.digitaltwins.core.BasicDigitalTwin;
import domain.request.infoRequest.InfoRequestId;
import domain.request.serviceRequest.ServiceRequestId;
import utils.Constants;

import java.util.ArrayList;

public class GetRequest {
    /**
     * Get all digital twin service request
     *
     * @return list of all service request
     */
    public static ArrayList<ServiceRequestId> getAllServiceRequestId(){
        ArrayList<ServiceRequestId> serviceRequestIds = new ArrayList<>();
        String query = "SELECT $dtId FROM DIGITALTWINS WHERE IS_OF_MODEL('"+ Constants.SERVICE_REQUEST_MODEL_ID + "')";
        PagedIterable<BasicDigitalTwin> pageableResponse = Client.getClient().query(query, BasicDigitalTwin.class);
        pageableResponse.forEach(r-> serviceRequestIds.add(new ServiceRequestId(r.getId())));
        return serviceRequestIds;
    }

    /**
     * Get all digital twin info request
     *
     * @return list of all info request
     */
    public static ArrayList<InfoRequestId> getAllInfoRequestId(){
        ArrayList<InfoRequestId> infoRequestIds = new ArrayList<>();
        String query = "SELECT $dtId FROM DIGITALTWINS WHERE IS_OF_MODEL('"+ Constants.INFO_REQUEST_MODEL_ID + "')";
        PagedIterable<BasicDigitalTwin> pageableResponse = Client.getClient().query(query, BasicDigitalTwin.class);
        pageableResponse.forEach(r-> infoRequestIds.add(new InfoRequestId(r.getId())));
        return infoRequestIds;
    }
}
