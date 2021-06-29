/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package digitalTwinsAPI;

import com.azure.core.models.JsonPatchDocument;
import domain.transport.TransportId;

import java.time.LocalDateTime;

public class TransportEnded {
    private TransportEnded(){}

    /**
     * Set end of a transport
     *
     * @param id of transport
     */
    public static void setTransportEnded(TransportId id){
        JsonPatchDocument updateOp = new JsonPatchDocument()
                .appendAdd("/endDateTime", LocalDateTime.now());

        Client.getClient().updateDigitalTwin(id.getId(), updateOp);
    }
}
