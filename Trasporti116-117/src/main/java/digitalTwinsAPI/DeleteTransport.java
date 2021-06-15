/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package digitalTwinsAPI;

import com.azure.digitaltwins.core.BasicRelationship;
import com.azure.digitaltwins.core.implementation.models.ErrorResponseException;
import domain.transport.TransportId;
import utils.AzureErrorMessage;
import utils.errorCode.DeleteDigitalTwinStatusCode;

public class DeleteTransport {
    public static DeleteDigitalTwinStatusCode deleteTransport(TransportId transportId) {
        try {
            Client.getClient().listRelationships(transportId.getId(), BasicRelationship.class)
                    .forEach(rel -> Client.getClient().deleteRelationship(transportId.getId(), rel.getId()));
            Client.getClient().deleteDigitalTwin(transportId.getId());
            return DeleteDigitalTwinStatusCode.DELETED;
        } catch(ErrorResponseException ex){
            if(ex.getLocalizedMessage().contains(AzureErrorMessage.RELATIONSHIP_NOT_DELETED)) {
                return DeleteDigitalTwinStatusCode.TRANSPORT_RELATION_EXISTING;
            }
            throw ex;
        }
    }
}
