/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package digitalTwinsAPI;

import domain.patient.PatientFiscalCode;
import domain.request.infoRequest.InfoRequestId;
import domain.request.serviceRequest.BookingTransportId;
import domain.request.serviceRequest.ServiceRequestId;
import domain.transport.TransportId;

import java.time.LocalDateTime;

/**
 * Generate digital twin id
 */
public class GenerateId {
    private GenerateId(){}

    /**
     * Generate a unique BookingTransportId
     *
     * @param  patientId patient fiscal code
     * @param dataOra date of booking
     * @return a new BookingTransportId
     */
    public static BookingTransportId generateBookingTransportId(PatientFiscalCode patientId, LocalDateTime dataOra){
        return new BookingTransportId("booking" + dataOra.toLocalDate() + "_" + dataOra.getHour() + "-" + dataOra.getMinute() + "_" + patientId.getFiscalCode());
    }

    /**
     * Generate a unique ServiceRequestId
     *
     * @param dataOra date of service request
     * @return a new ServiceRequestId
     */
    public static ServiceRequestId generateServiceRequestId(LocalDateTime dataOra){
        return new ServiceRequestId("serviceRequest_"+dataOra.toLocalDate() + "_" + dataOra.getHour() + "-" + dataOra.getMinute() + "_");
    }

    /**
     * Generate a unique InfoRequestId
     *
     * @param dataOra date of info request
     * @return a new InfoRequestId
     */
    public static InfoRequestId generateInfoRequestId(LocalDateTime dataOra){
        return new InfoRequestId("infoRequest_"+dataOra.toLocalDate() + "_" + dataOra.getHour() + "-" + dataOra.getMinute() + "_");
    }

    /**
     * Generate a unique TransportId
     *
     * @param dataOra date of transport
     * @param patientId patient fiscal code
     *
     * @return a new TransportId
     */
    public static TransportId generateTransportId(PatientFiscalCode patientId, LocalDateTime dataOra){
        return new TransportId(dataOra.toLocalDate() + "_" + dataOra.getHour() + "-" + dataOra.getMinute() + "_" + patientId.getFiscalCode());
    }
}
