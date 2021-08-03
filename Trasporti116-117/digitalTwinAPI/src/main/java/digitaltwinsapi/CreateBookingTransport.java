/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */
package digitaltwinsapi;

import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicDigitalTwinMetadata;
import com.azure.digitaltwins.core.BasicRelationship;
import domain.patient.PatientFiscalCode;
import domain.request.serviceRequest.BookingRoute;
import domain.request.serviceRequest.BookingTransportId;
import domain.request.serviceRequest.ServiceRequestId;
import utils.Constants;

import java.time.LocalDateTime;

/**
 * Contains create boooking transport digital twin API
 */
public final class CreateBookingTransport {
    private CreateBookingTransport() { }
    /**
     * Create a booking digital twin by number
     *
     * @param dateTime date of booking
     * @param route route of booking
     * @param patientId patient assigned to the booking
     * @param serviceRequestId service request relating to the booking
     *
     * @return id of the booking created
     */
    public static BookingTransportId createBookingTransport(final LocalDateTime dateTime, final BookingRoute route, final PatientFiscalCode patientId, final ServiceRequestId serviceRequestId) {
        BookingTransportId bookingTransportId = GenerateId.generateBookingTransportId(patientId, dateTime);

        BasicDigitalTwin bookingTransportDT = new BasicDigitalTwin(bookingTransportId.getId())
                .setMetadata(
                        new BasicDigitalTwinMetadata().setModelId(Constants.BOOKING_MODEL_ID)
                )
                .addToContents("dateTime", dateTime)
                .addToContents("takeOwnership", false)
                .addToContents("route", route);

        Client.getClient().createOrReplaceDigitalTwin(bookingTransportId.getId(), bookingTransportDT, BasicDigitalTwin.class);

        //Create relationship with patient
       Client.getClient().createOrReplaceRelationship(
                bookingTransportId.getId(),
                bookingTransportId.getId() + "to" + patientId.getFiscalCode(),
                new BasicRelationship(
                        bookingTransportId.getId() + "to" + patientId.getFiscalCode(),
                        bookingTransportId.getId(),
                        patientId.getFiscalCode(),
                        "transport"),
                BasicRelationship.class);

        //Create relationship with serviceRequest
        Client.getClient().createOrReplaceRelationship(
                bookingTransportId.getId(),
                bookingTransportId.getId() + "to" + serviceRequestId.getId(),
                new BasicRelationship(
                        bookingTransportId.getId() + "to" + serviceRequestId.getId(),
                        bookingTransportId.getId(),
                        serviceRequestId.getId(),
                        "requestBy"),
                BasicRelationship.class);

        return bookingTransportId;
    }
}
