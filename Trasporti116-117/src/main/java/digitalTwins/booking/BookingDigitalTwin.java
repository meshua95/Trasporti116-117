/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package digitalTwins.booking;

import com.azure.core.http.rest.PagedIterable;
import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicDigitalTwinMetadata;
import com.azure.digitaltwins.core.BasicRelationship;
import digitalTwins.Client;
import domain.patientBoundedContext.PatientFiscalCode;
import domain.requestBoundedContext.serviceRequest.BookingTransportId;
import domain.requestBoundedContext.serviceRequest.Route;
import utils.Constants;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookingDigitalTwin {

    public static BookingTransportId createBookingTransport(LocalDateTime dateTime, Route route, PatientFiscalCode patientId){
        BookingTransportId bookingTransportId = generateBookingTransportId(patientId, dateTime);

        BasicDigitalTwin bookingTransportDT = new BasicDigitalTwin(bookingTransportId.getId())
                .setMetadata(
                        new BasicDigitalTwinMetadata().setModelId(Constants.BOOKING_MODEL_ID)
                )
                .addToContents("dateTime", dateTime)
                .addToContents("route", route);

        BasicDigitalTwin basicTwinResponse = Client.getClient().createOrReplaceDigitalTwin(bookingTransportId.getId(), bookingTransportDT, BasicDigitalTwin.class);
        System.out.println(basicTwinResponse.getId());

        BasicRelationship createdRelationship = Client.getClient().createOrReplaceRelationship(
                bookingTransportId.getId(),
                bookingTransportId.getId() + "to" + patientId.getFiscalCode(),
                new BasicRelationship(
                        bookingTransportId.getId() + "to" + patientId.getFiscalCode(),
                        bookingTransportId.getId(),
                        patientId.getFiscalCode(),
                        "transport"),
                BasicRelationship.class);

        System.out.println(createdRelationship.getId());
        return bookingTransportId;
    }

    public static void deleteBookingTransport(BookingTransportId bookingTransportId) {
        Client.getClient().listRelationships(bookingTransportId.getId(), BasicRelationship.class)
                .forEach(rel -> Client.getClient().deleteRelationship(bookingTransportId.getId(), rel.getId()));
        Client.getClient().deleteDigitalTwin(bookingTransportId.getId());
    }

    public static void deleteAllTransport(List<BookingTransportId> dtId) {
        dtId.forEach(BookingDigitalTwin::deleteBookingTransport);
    }

    public static ArrayList<BookingTransportId> getAllBookingId(){
        ArrayList<BookingTransportId> bookingIds = new ArrayList<>();
        String query = "SELECT $dtId FROM DIGITALTWINS WHERE IS_OF_MODEL('"+ Constants.BOOKING_MODEL_ID + "')";
        PagedIterable<BasicDigitalTwin> pageableResponse = Client.getClient().query(query, BasicDigitalTwin.class);
        pageableResponse.forEach(r-> bookingIds.add(new BookingTransportId(r.getId())));
        return bookingIds;
    }

  /*  public static ArrayList<BookingTransportId> getTransportOfAmbulance(AmbulanceId id){
        ArrayList<BookingTransportId> serviceRequestIds = new ArrayList<>();
        String query = "SELECT source " +
                "FROM DIGITALTWINS source " +
                "JOIN target RELATED source.use " +
                "WHERE target.$dtId = '"+ id.getAmbulanceId() +"'";
        PagedIterable<BasicDigitalTwin> pageableResponse = Client.getClient().query(query, BasicDigitalTwin.class);
        pageableResponse.forEach(r-> serviceRequestIds.add(new BookingTransportId(r.getId())));
        return serviceRequestIds;
    }*/

    public static BookingTransportId generateBookingTransportId(PatientFiscalCode patientId, LocalDateTime dataOra){
        return new BookingTransportId(dataOra.toLocalDate() + "_" + dataOra.getHour() + "-" + dataOra.getMinute() + "_" + patientId.getFiscalCode());
    }
}
