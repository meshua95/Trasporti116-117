/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */
package digitalTwinsAPI;

import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicDigitalTwinMetadata;
import com.azure.digitaltwins.core.BasicRelationship;
import domain.patient.PatientFiscalCode;
import domain.request.serviceRequest.BookingRoute;
import domain.request.serviceRequest.BookingTransportId;
import domain.request.serviceRequest.ServiceRequestId;
import utils.Constants;

import java.time.LocalDateTime;

public class CreateBookingTransport {
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
    public static BookingTransportId createBookingTransport(LocalDateTime dateTime, BookingRoute route, PatientFiscalCode patientId, ServiceRequestId serviceRequestId){
        BookingTransportId bookingTransportId = GenerateId.generateBookingTransportId(patientId, dateTime);

        BasicDigitalTwin bookingTransportDT = new BasicDigitalTwin(bookingTransportId.getId())
                .setMetadata(
                        new BasicDigitalTwinMetadata().setModelId(Constants.BOOKING_MODEL_ID)
                )
                .addToContents("dateTime", dateTime)
                .addToContents("takeOwnership", false)
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
}
