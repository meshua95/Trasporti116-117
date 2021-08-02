/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package digitaltwinsapi;

import com.azure.core.models.JsonPatchDocument;
import domain.transport.TransportId;

import java.time.LocalDateTime;

/**
 * Set ended transport digital twin
 */
public final class TransportEnded {
    private TransportEnded() { }

    /**
     * Set end of a transport
     *
     * @param id of transport
     */
    public static void setTransportEnded(final TransportId id) {
        JsonPatchDocument updateOp = new JsonPatchDocument()
                .appendAdd("/endDateTime", LocalDateTime.now());

        Client.getClient().updateDigitalTwin(id.getId(), updateOp);
    }
}
