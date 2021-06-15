/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */
package digitalTwinsAPI;

import com.azure.core.http.rest.PagedIterable;
import domain.request.serviceRequest.BookingTransportId;
import org.json.simple.JSONObject;
import utils.Constants;
import utils.errorCode.QueryTimeOutException;

public class GetRoute {

    /**
     * Get the route for specific booking
     *
     * @param  bookingId booking of which you want the route
     * @return JSONObject representing the route
     */
    public static JSONObject getRouteByBookingId(BookingTransportId bookingId) throws QueryTimeOutException {
        String query = "SELECT route FROM DIGITALTWINS WHERE IS_OF_MODEL('"+ Constants.BOOKING_MODEL_ID + "') AND $dtId = '" + bookingId.getId() + "'";

        PagedIterable<JSONObject> pageableResponse = Client.getClient().query(query, JSONObject.class);

        long startTime = System.currentTimeMillis();
        while(pageableResponse.stream().findFirst().isEmpty()){
            if (System.currentTimeMillis() - startTime > QueryTimeOutException.TIME_OUT)
                throw new QueryTimeOutException();
        }

        return pageableResponse.stream().findFirst().get();
    }
}
