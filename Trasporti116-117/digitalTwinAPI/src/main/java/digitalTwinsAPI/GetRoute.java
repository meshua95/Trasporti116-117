/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */
package digitalTwinsAPI;

import com.azure.core.http.rest.PagedIterable;
import domain.request.serviceRequest.BookingTransportId;
import org.json.simple.JSONObject;
import utils.Constants;
import utils.WaitForClientResponse;
import utils.errorCode.QueryTimeOutException;

/**
 * Get route digital twin API
 */
public class GetRoute {
    private GetRoute(){}

    /**
     * Get the route for specific booking
     *
     * @param  bookingId booking of which you want the route
     * @return JSONObject representing the route
     * @throws QueryTimeOutException if the server takes too long to respond
     */
    public static JSONObject getRouteByBookingId(BookingTransportId bookingId) throws QueryTimeOutException {
        String query = "SELECT route FROM DIGITALTWINS WHERE IS_OF_MODEL('"+ Constants.BOOKING_MODEL_ID + "') AND $dtId = '" + bookingId.getId() + "'";

        PagedIterable<JSONObject> pageableResponse = Client.getClient().query(query, JSONObject.class);

        WaitForClientResponse.waitForClientResponse(pageableResponse);

        return pageableResponse.stream().findFirst().get();
    }
}
