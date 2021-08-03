/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package digitaltwinsapi;

import com.azure.core.http.rest.PagedIterable;
import com.azure.digitaltwins.core.BasicDigitalTwin;
import domain.request.serviceRequest.BookingTransportId;
import utils.Constants;
import utils.errorCode.QueryTimeOutException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Get booking digital twin API
 */
public final class GetBooking {
    private GetBooking() { }

    /**
     * Get all booking
     * @throws QueryTimeOutException if the server takes too long to respond
     *  @return all booking
     */
    public static List<BookingTransportId> getAllBookingId() throws QueryTimeOutException {
        List<BookingTransportId> bookingIds = new ArrayList<>();
        String query = "SELECT $dtId FROM DIGITALTWINS WHERE IS_OF_MODEL('" + Constants.BOOKING_MODEL_ID + "')";
        PagedIterable<BasicDigitalTwin> pageableResponse = Client.getClient().query(query, BasicDigitalTwin.class);

      //  WaitForClientResponse.waitForClientResponseIfExist(pageableResponse);

        pageableResponse.forEach(r -> bookingIds.add(new BookingTransportId(r.getId())));
        return bookingIds;
    }

    /**
     * Get all booking still to be made for specific day
     *
     * @param  dateTime date of the booking you want to take
     *
     * @throws QueryTimeOutException if the server takes too long to respond
     *
     * @return list of booking
     */
    public static List<BookingTransportId> getAllBookingToDoForTheDay(final LocalDateTime dateTime) throws QueryTimeOutException {
        List<BookingTransportId> bookingIds = new ArrayList<>();
        String query = "SELECT $dtId "
                + "FROM DIGITALTWINS "
                + "WHERE STARTSWITH(dateTime, '" + dateTime.toLocalDate() + "') AND takeOwnership = false";
        PagedIterable<BasicDigitalTwin> pageableResponse = Client.getClient().query(query, BasicDigitalTwin.class);

        pageableResponse.forEach(r -> bookingIds.add(new BookingTransportId(r.getId())));
        return bookingIds;
    }
}
