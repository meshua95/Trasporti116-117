/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package digitalTwins.booking;

import com.azure.core.http.rest.PagedFlux;
import com.azure.core.http.rest.PagedIterable;
import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicDigitalTwinMetadata;
import com.azure.digitaltwins.core.BasicRelationship;
import digitalTwins.Client;
import domain.patientBoundedContext.PatientFiscalCode;
import domain.requestBoundedContext.serviceRequest.BookingTransportId;
import domain.requestBoundedContext.serviceRequest.BookingRoute;
import domain.requestBoundedContext.serviceRequest.ServiceRequestId;

import org.json.simple.JSONObject;
import reactor.core.publisher.Mono;
import utils.Constants;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class BookingDigitalTwin {

    public static BookingTransportId createBookingTransport(LocalDateTime dateTime, BookingRoute route, PatientFiscalCode patientId, ServiceRequestId serviceRequestId){
        BookingTransportId bookingTransportId = generateBookingTransportId(patientId, dateTime);

        BasicDigitalTwin bookingTransportDT = new BasicDigitalTwin(bookingTransportId.getId())
                .setMetadata(
                        new BasicDigitalTwinMetadata().setModelId(Constants.BOOKING_MODEL_ID)
                )
                .addToContents("dateTime", dateTime)
                .addToContents("route", route);

        BasicDigitalTwin basicTwinResponse = Client.getClient().createOrReplaceDigitalTwin(bookingTransportId.getId(), bookingTransportDT, BasicDigitalTwin.class);
        System.out.println(basicTwinResponse.getId());

        //Create relationship with patient
        BasicRelationship createdRelationshipPatient = Client.getClient().createOrReplaceRelationship(
                bookingTransportId.getId(),
                bookingTransportId.getId() + "to" + patientId.getFiscalCode(),
                new BasicRelationship(
                        bookingTransportId.getId() + "to" + patientId.getFiscalCode(),
                        bookingTransportId.getId(),
                        patientId.getFiscalCode(),
                        "transport"),
                BasicRelationship.class);

        //Create relationship with serviceRequest
        BasicRelationship createdRelationshipServiceRequest = Client.getClient().createOrReplaceRelationship(
                bookingTransportId.getId(),
                bookingTransportId.getId() + "to" + serviceRequestId.getserviceRequestId(),
                new BasicRelationship(
                        bookingTransportId.getId() + "to" + serviceRequestId.getserviceRequestId(),
                        bookingTransportId.getId(),
                        serviceRequestId.getserviceRequestId(),
                        "requestBy"),
                BasicRelationship.class);

        System.out.println(createdRelationshipPatient.getId());
        System.out.println(createdRelationshipServiceRequest.getId());
        return bookingTransportId;
    }

    public static void deleteBookingTransport(BookingTransportId bookingTransportId) {
        Client.getClient().listRelationships(bookingTransportId.getId(), BasicRelationship.class)
                .forEach(rel -> Client.getClient().deleteRelationship(bookingTransportId.getId(), rel.getId()));
        Client.getClient().deleteDigitalTwin(bookingTransportId.getId());
    }

    public static void deleteAllBooking(List<BookingTransportId> dtId) {
        dtId.forEach(BookingDigitalTwin::deleteBookingTransport);
    }

    public static ArrayList<BookingTransportId> getAllBookingId(){
        ArrayList<BookingTransportId> bookingIds = new ArrayList<>();
        String query = "SELECT $dtId FROM DIGITALTWINS WHERE IS_OF_MODEL('"+ Constants.BOOKING_MODEL_ID + "')";
        PagedIterable<BasicDigitalTwin> pageableResponse = Client.getClient().query(query, BasicDigitalTwin.class);
        pageableResponse.forEach(r-> bookingIds.add(new BookingTransportId(r.getId())));
        return bookingIds;
    }

    public static JSONObject getRouteByBookingId(BookingTransportId bookingId){
        String query = "SELECT route FROM DIGITALTWINS WHERE IS_OF_MODEL('"+ Constants.BOOKING_MODEL_ID + "') AND $dtId = '" + bookingId.getId() + "'";
        final int TIME_OUT_CONNECTION = 4000;

        PagedIterable<JSONObject> paged = Client.getClient().query(query, JSONObject.class);
        long startTime = System.currentTimeMillis();

        while(paged.stream().findFirst().isEmpty()){
            if (System.currentTimeMillis() - startTime > TIME_OUT_CONNECTION)
                return null;
        }
        return paged.stream().findFirst().get();

       /* PagedFlux<JSONObject> res = Client.getClientAsync().query(query, JSONObject.class);

        final CountDownLatch queryLatch = new CountDownLatch(1);

        res.doOnError(throwable -> System.out.println("Query error: " + throwable))
                .doOnTerminate(queryLatch::countDown)
                .subscribe();

        try {
            queryLatch.await(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return res.log().blockFirst();*/
    }

    public static String getPatientIdByBookingId(BookingTransportId bookingId){
        final int TIME_OUT_CONNECTION = 4000;

        String query = "SELECT target.$dtId " +
                "FROM DIGITALTWINS source " +
                "JOIN target RELATED source.transport " +
                "WHERE source.$dtId = '"+ bookingId.getId() +"'";

        PagedIterable<JSONObject> paged = Client.getClient().query(query, JSONObject.class);
        long startTime = System.currentTimeMillis();

        while(paged.stream().findFirst().isEmpty()){
            if (System.currentTimeMillis() - startTime > TIME_OUT_CONNECTION)
                return "Error";
        }

        return paged.stream().findFirst().get().get("$dtId").toString();
    }

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

    public static ArrayList<BookingTransportId> getAllBookingForToday(){
        return getAllBookingForTheDay(LocalDateTime.now());
    }

    public static BookingTransportId generateBookingTransportId(PatientFiscalCode patientId, LocalDateTime dataOra){
        return new BookingTransportId("booking" + dataOra.toLocalDate() + "_" + dataOra.getHour() + "-" + dataOra.getMinute() + "_" + patientId.getFiscalCode());
    }
}
