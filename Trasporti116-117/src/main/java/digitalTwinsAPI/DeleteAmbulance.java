/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package digitalTwinsAPI;

import com.azure.digitaltwins.core.BasicRelationship;
import com.azure.digitaltwins.core.implementation.models.ErrorResponseException;
import domain.transport.ambulance.AmbulanceId;
import utils.AzureErrorMessage;
import utils.errorCode.DeleteDigitalTwinStatusCode;

public class DeleteAmbulance {
    /**
     * Delete an ambulance digital twin
     *
     * @param  ambulanceId  id of the ambulance to be canceled
     * @return      the DeleteDigitalTwinStatusCode
     */
    public static DeleteDigitalTwinStatusCode deleteAmbulance(AmbulanceId ambulanceId) {
        try{
            Client.getClient().listRelationships(ambulanceId.getAmbulanceId(), BasicRelationship.class)
                    .forEach(rel -> Client.getClient().deleteRelationship(ambulanceId.getAmbulanceId(), rel.getId()));
            Client.getClient().deleteDigitalTwin(ambulanceId.getAmbulanceId());
            Client.getClient().deleteDigitalTwin(ambulanceId.getGpsId());

            return DeleteDigitalTwinStatusCode.DELETED;
        } catch(ErrorResponseException e){
            if(e.getLocalizedMessage().contains(AzureErrorMessage.RELATIONSHIP_NOT_DELETED)) {
                return DeleteDigitalTwinStatusCode.TRANSPORT_RELATION_EXISTING;
            }
            throw e;
        }
    }

}
