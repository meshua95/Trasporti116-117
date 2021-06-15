/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package digitalTwinsAPI;

import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicDigitalTwinMetadata;
import com.azure.digitaltwins.core.BasicRelationship;
import domain.transport.ambulance.AmbulanceId;
import domain.patient.PatientFiscalCode;
import domain.request.serviceRequest.BookingTransportId;
import domain.transport.operator.OperatorId;
import domain.transport.TransportId;
import org.json.simple.JSONObject;
import utils.Constants;
import utils.errorCode.QueryTimeOutException;

import java.time.LocalDateTime;

public class StartTransport {
    /**
     * Create a Transport digital twin
     *
     * @param bookingId relative booking
     * @param ambulanceId ambulance doing the transport
     * @param operatorId operator doing the transport
     *
     * @return id of the request created
     */
    public static TransportId startTransport(BookingTransportId bookingId, AmbulanceId ambulanceId, OperatorId operatorId) throws QueryTimeOutException {
        LocalDateTime dateTime = LocalDateTime.now();
        JSONObject route = GetRoute.getRouteByBookingId(bookingId);
        PatientFiscalCode patientId = GetPatient.getPatientIdByBookingId(bookingId);

        TransportId transportId = GenerateId.generateTransportId(patientId, dateTime);
        System.out.println(transportId);

        BasicDigitalTwin transportDT = new BasicDigitalTwin(transportId.getId())
                .setMetadata(
                        new BasicDigitalTwinMetadata().setModelId(Constants.TRANSPORT_MODEL_ID)
                )
                .addToContents("startDateTime", dateTime)
                .addToContents("endDateTime", null)
                .addToContents("route", route.get("route"));

        BasicDigitalTwin basicTwinResponse = Client.getClient().createOrReplaceDigitalTwin(transportId.getId(), transportDT, BasicDigitalTwin.class);
        System.out.println(basicTwinResponse.getId());

        //add relationship whit ambulance
        createTransportRelationship(transportId, ambulanceId.getAmbulanceId(), "use");

        //add relationship whit patient
        createTransportRelationship(transportId, patientId.getFiscalCode(), "transport");

        //add relationship whit operator
        createTransportRelationship(transportId, operatorId.getOperatorId(), "drive");

        //add relationship with bookingTransport
        createTransportRelationship(transportId, bookingId.getId(), "related");

        return transportId;
    }

    private static void createTransportRelationship(TransportId transportId, String targetId, String relationshipName){
        BasicRelationship trasportoToTargetRelationship =
                new BasicRelationship(
                        transportId.getId() + "to" + targetId,
                        transportId.getId(),
                        targetId,
                        relationshipName);

        System.out.println(targetId);

        Client.getClient().createOrReplaceRelationship(
                transportId.getId(),
                transportId.getId() + "to" + targetId,
                trasportoToTargetRelationship,
                BasicRelationship.class);
    }
}
