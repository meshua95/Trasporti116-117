/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */
package digitaltwinsapi;

import domain.request.infoRequest.InfoRequestId;
import domain.request.serviceRequest.ServiceRequestId;

/**
 * Contains delete request digital twin API
 */
public final class DeleteRequest {
    private DeleteRequest() { }
    /**
     * Delete a service request digital twin
     *
     * @param  serviceRequestId  id of the request to be canceled
     */
    public static void deleteServiceRequest(final ServiceRequestId serviceRequestId) {
        Client.getClient().deleteDigitalTwin(serviceRequestId.getserviceRequestId());
    }

    /**
     * Delete a info request digital twin
     *
     * @param  infoRequestId  id of the request to be canceled
     */
    public static void deleteInfoRequest(final InfoRequestId infoRequestId) {
        Client.getClient().deleteDigitalTwin(infoRequestId.getInfoRequestId());
    }
}
