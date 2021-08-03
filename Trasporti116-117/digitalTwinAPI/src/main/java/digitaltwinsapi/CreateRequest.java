/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */
package digitaltwinsapi;

import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicDigitalTwinMetadata;
import domain.request.infoRequest.InfoRequestDescription;
import domain.request.infoRequest.InfoRequestId;
import domain.request.serviceRequest.ServiceRequestId;
import utils.Constants;

import java.time.LocalDateTime;

/**
 * Contains create request digital twin API
 */
public final class CreateRequest {
    private CreateRequest() { }
    /**
     * Create a information request digital twin
     *
     * @param dateTime date of the request
     * @param description description of the request
     *
     * @return id of the request created
     */
    public static InfoRequestId createInfoRequest(final LocalDateTime dateTime, final InfoRequestDescription description) {
        InfoRequestId infoRequestId = GenerateId.generateInfoRequestId(dateTime);

        BasicDigitalTwin infoRequestDT = new BasicDigitalTwin(infoRequestId.getInfoRequestId())
                .setMetadata(
                        new BasicDigitalTwinMetadata().setModelId(Constants.INFO_REQUEST_MODEL_ID)
                )
                .addToContents("dateTime", dateTime)
                .addToContents("description", description.getDescription());

       Client.getClient().createOrReplaceDigitalTwin(infoRequestId.getInfoRequestId(), infoRequestDT, BasicDigitalTwin.class);

        return infoRequestId;
    }

    /**
     * Create a service request digital twin
     *
     * @param dateTime date of the request
     *
     * @return id of the request created
     */
    public static ServiceRequestId createServiceRequest(final LocalDateTime dateTime) {
        ServiceRequestId serviceRequestId = GenerateId.generateServiceRequestId(dateTime);

        BasicDigitalTwin serviceRequestDT = new BasicDigitalTwin(serviceRequestId.getId())
                .setMetadata(
                        new BasicDigitalTwinMetadata().setModelId(Constants.SERVICE_REQUEST_MODEL_ID)
                )
                .addToContents("dateTime", dateTime);

        Client.getClient().createOrReplaceDigitalTwin(serviceRequestId.getId(), serviceRequestDT, BasicDigitalTwin.class);

        return serviceRequestId;
    }
}
