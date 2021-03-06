/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */
package digitaltwinsapi;

import com.azure.core.models.JsonPatchDocument;
import domain.request.serviceRequest.BookingTransportId;

/**
 * Set Take Ownership of booking
 */
public final class SetTakeOwnership {
    private SetTakeOwnership() { }

    /**
     * Set the take ownership of a booking
     *
     * @param  bookingId to set take ownership
     */
    public static void setTakeOwnership(final BookingTransportId bookingId) {
        JsonPatchDocument updateOp = new JsonPatchDocument().appendAdd("/takeOwnership", true);
        Client.getClient().updateDigitalTwin(bookingId.getId(), updateOp);
    }
}
