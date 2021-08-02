/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package digitaltwinsapi;

import com.azure.digitaltwins.core.BasicRelationship;
import com.azure.digitaltwins.core.implementation.models.ErrorResponseException;
import domain.transport.TransportId;
import utils.AzureErrorMessage;
import utils.errorCode.DeleteDigitalTwinStatusCode;

/**
 * Contains delete transport digital twin API
 */
public class DeleteTransport {
    private DeleteTransport(){}
    /**
     * Delete a transport digital twin
     *
     * @param  transportId  id of the request to be canceled
     * @return DeleteDigitalTwinStatusCode
     */
    public static DeleteDigitalTwinStatusCode deleteTransport(TransportId transportId) {
        try {
            Client.getClient().listRelationships(transportId.getId(), BasicRelationship.class)
                    .forEach(rel -> Client.getClient().deleteRelationship(transportId.getId(), rel.getId()));
            Client.getClient().deleteDigitalTwin(transportId.getId());
            return DeleteDigitalTwinStatusCode.DELETED;
        } catch(ErrorResponseException ex){
            if(ex.getLocalizedMessage().contains(AzureErrorMessage.RELATIONSHIP_NOT_DELETED)) {
                return DeleteDigitalTwinStatusCode.RELATION_EXISTING;
            }
            throw ex;
        }
    }
}
