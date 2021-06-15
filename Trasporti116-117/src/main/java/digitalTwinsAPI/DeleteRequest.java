/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */
package digitalTwinsAPI;

import com.azure.digitaltwins.core.BasicRelationship;
import domain.request.infoRequest.InfoRequestId;
import domain.request.serviceRequest.ServiceRequestId;

public class DeleteRequest {
    /**
     * Delete a service request digital twin
     *
     * @param  serviceRequestId  id of the request to be canceled
     */
    public static void deleteServiceRequest(ServiceRequestId serviceRequestId) {
        Client.getClient().listRelationships(serviceRequestId.getserviceRequestId(), BasicRelationship.class)
                .forEach(rel -> Client.getClient().deleteRelationship(serviceRequestId.getserviceRequestId(), rel.getId()));
        Client.getClient().deleteDigitalTwin(serviceRequestId.getserviceRequestId());
    }

    /**
     * Delete a info request digital twin
     *
     * @param  infoRequestId  id of the request to be canceled
     */
    public static void deleteInfoRequest(InfoRequestId infoRequestId) {
        Client.getClient().listRelationships(infoRequestId.getInfoRequestId(), BasicRelationship.class)
                .forEach(rel -> Client.getClient().deleteRelationship(infoRequestId.getInfoRequestId(), rel.getId()));
        Client.getClient().deleteDigitalTwin(infoRequestId.getInfoRequestId());
    }
}
