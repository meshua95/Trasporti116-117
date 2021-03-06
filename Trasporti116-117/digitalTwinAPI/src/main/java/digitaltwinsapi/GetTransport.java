/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package digitaltwinsapi;

import com.azure.core.http.rest.PagedIterable;
import com.azure.digitaltwins.core.BasicDigitalTwin;
import domain.transport.TransportId;
import utils.Constants;
import utils.errorCode.QueryTimeOutException;

import java.util.ArrayList;
import java.util.List;

/**
 * Get transport digital twin API
 */
public final class GetTransport {
    private GetTransport() { }

    /**
     * Get all transport in progress
     *
     * @return list of TransportId in progress
     * @throws QueryTimeOutException if the server takes too long to respond
     */
    public static List<TransportId> getAllTransportInProgress() throws QueryTimeOutException {
        List<TransportId> transportIds = new ArrayList<>();
        String query = "SELECT * "
                + "FROM DIGITALTWINS "
                + "WHERE IS_OF_MODEL('" + Constants.TRANSPORT_MODEL_ID + "') AND NOT IS_DEFINED ( endDateTime )";

        PagedIterable<BasicDigitalTwin> pageableResponse = Client.getClient().query(query, BasicDigitalTwin.class);

        pageableResponse.forEach(r -> transportIds.add(new TransportId(r.getId())));
        return transportIds;
    }
}
