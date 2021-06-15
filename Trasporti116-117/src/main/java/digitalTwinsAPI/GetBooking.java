/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package digitalTwinsAPI;

import com.azure.core.http.rest.PagedIterable;
import com.azure.digitaltwins.core.BasicDigitalTwin;
import domain.request.serviceRequest.BookingTransportId;
import utils.Constants;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class GetBooking {
    /**
     * Get all booking
     *
     * @return all booking
     */
    public static ArrayList<BookingTransportId> getAllBookingId(){
        ArrayList<BookingTransportId> bookingIds = new ArrayList<>();
        String query = "SELECT $dtId FROM DIGITALTWINS WHERE IS_OF_MODEL('"+ Constants.BOOKING_MODEL_ID + "')";
        PagedIterable<BasicDigitalTwin> pageableResponse = Client.getClient().query(query, BasicDigitalTwin.class);
        pageableResponse.forEach(r-> bookingIds.add(new BookingTransportId(r.getId())));
        return bookingIds;
    }

    /**
     * Get all booking still to be made for specific day
     *
     * @param  dateTime date of the booking you want to take
     * @return list of booking
     */
    public static ArrayList<BookingTransportId> getAllBookingToDoForTheDay(LocalDateTime dateTime){
        ArrayList<BookingTransportId> bookingIds = new ArrayList<>();
        String query = "SELECT $dtId " +
                "FROM DIGITALTWINS " +
                "WHERE STARTSWITH(dateTime, '" + dateTime.toLocalDate() + "') AND takeOwnership = false";
        PagedIterable<BasicDigitalTwin> pageableResponse = Client.getClient().query(query, BasicDigitalTwin.class);
        pageableResponse.forEach(r-> bookingIds.add(new BookingTransportId(r.getId())));
        return bookingIds;
    }

    /**
     * Get all booking for specific day
     *
     * @param  date date of the booking you want to take
     * @return list of booking
     */
    public static ArrayList<BookingTransportId> getAllBookingForTheDay(LocalDateTime date){
        ArrayList<BookingTransportId> serviceRequestIds = new ArrayList<>();
        String query = "SELECT * " +
                "FROM DIGITALTWINS " +
                "WHERE IS_OF_MODEL('" + Constants.BOOKING_MODEL_ID + "') " +
                "AND STARTSWITH(dateTime, '" + date.toLocalDate() + "')";
        PagedIterable<BasicDigitalTwin> pageableResponse = Client.getClient().query(query, BasicDigitalTwin.class);
        pageableResponse.forEach(r-> serviceRequestIds.add(new BookingTransportId(r.getId())));

        return serviceRequestIds;
    }

    /**
     * Get all booking still to be made for today
     *
     * @return list of booking
     */
    public static ArrayList<BookingTransportId> getAllBookingForToday(){
        return getAllBookingForTheDay(LocalDateTime.now());
    }
}
