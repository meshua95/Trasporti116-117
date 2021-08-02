/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */
package digitaltwinsapi;

import com.azure.core.http.rest.PagedIterable;
import com.azure.digitaltwins.core.BasicDigitalTwin;
import domain.transport.ambulance.AmbulanceId;
import utils.Constants;
import utils.errorCode.QueryTimeOutException;

import java.util.ArrayList;
import java.util.List;

/**
 * Get ambulance digital twin API
 */
public final class GetAmbulance {
    private GetAmbulance() { }

    /**
     * Get all digital twin ambulances available
     *
     * @throws QueryTimeOutException if the server takes too long to respond
     *
     * @return id of all ambulances available
     */
    public static List<AmbulanceId> getAllAmbulanceIdTwins() throws QueryTimeOutException {
        List<AmbulanceId> ambulanzeIds = new ArrayList<>();
            String query = "SELECT $dtId FROM DIGITALTWINS WHERE IS_OF_MODEL('" + Constants.AMBULANCE_MODEL_ID + "')";
        PagedIterable<BasicDigitalTwin> pageableResponse = Client.getClient().query(query, BasicDigitalTwin.class);

       // WaitForClientResponse.waitForClientResponseIfExist(pageableResponse);

        pageableResponse.forEach(r -> ambulanzeIds.add(new AmbulanceId(r.getId())));
        return ambulanzeIds;
    }
}
