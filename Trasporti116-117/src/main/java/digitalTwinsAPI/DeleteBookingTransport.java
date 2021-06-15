/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */
/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */
package digitalTwinsAPI;

import com.azure.digitaltwins.core.BasicRelationship;
import com.azure.digitaltwins.core.implementation.models.ErrorResponseException;
import domain.request.serviceRequest.BookingTransportId;
import utils.AzureErrorMessage;
import utils.errorCode.DeleteBookingStatusCode;


public class DeleteBookingTransport {
    /**
     * Delete a booking digital twin
     *
     * @param  bookingTransportId  id of the booking to be canceled
     * @return the DeleteBookingStatusCode
     */
    public static DeleteBookingStatusCode deleteBookingTransport(BookingTransportId bookingTransportId) {
        try{
            Client.getClient().listRelationships(bookingTransportId.getId(), BasicRelationship.class)
                    .forEach(rel -> Client.getClient().deleteRelationship(bookingTransportId.getId(), rel.getId()));
            Client.getClient().deleteDigitalTwin(bookingTransportId.getId());

            return DeleteBookingStatusCode.DELETED;
        } catch(ErrorResponseException e){
            if(e.getLocalizedMessage().contains(AzureErrorMessage.RELATIONSHIP_NOT_DELETED)) {
                return DeleteBookingStatusCode.RELATION_EXISTING;
            }
            throw e;
        }
    }

}
