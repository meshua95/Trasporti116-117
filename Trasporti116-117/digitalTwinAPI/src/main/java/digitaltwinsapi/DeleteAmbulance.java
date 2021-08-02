/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package digitaltwinsapi;

import com.azure.digitaltwins.core.BasicRelationship;
import com.azure.digitaltwins.core.implementation.models.ErrorResponseException;
import domain.transport.ambulance.AmbulanceId;
import utils.AzureErrorMessage;
import utils.errorCode.DeleteDigitalTwinStatusCode;

/**
 * Contains delete ambulance digital twin API
 */
public final class DeleteAmbulance {
    private DeleteAmbulance() { }
    /**
     * Delete an ambulance digital twin
     *
     * @param  ambulanceId  id of the ambulance to be canceled
     * @return      the DeleteDigitalTwinStatusCode
     */
    public static DeleteDigitalTwinStatusCode deleteAmbulance(final AmbulanceId ambulanceId) {
        try {
            Client.getClient().listRelationships(ambulanceId.getAmbulanceId(), BasicRelationship.class)
                    .forEach(rel -> Client.getClient().deleteRelationship(ambulanceId.getAmbulanceId(), rel.getId()));
            Client.getClient().deleteDigitalTwin(ambulanceId.getAmbulanceId());
            Client.getClient().deleteDigitalTwin(ambulanceId.getGpsId());

            return DeleteDigitalTwinStatusCode.DELETED;
        } catch (ErrorResponseException e) {
            if (e.getLocalizedMessage().contains(AzureErrorMessage.RELATIONSHIP_NOT_DELETED)) {
                return DeleteDigitalTwinStatusCode.RELATION_EXISTING;
            }
            throw e;
        }
    }

}
