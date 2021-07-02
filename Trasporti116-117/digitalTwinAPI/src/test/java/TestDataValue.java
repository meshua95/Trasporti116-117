import domain.*;
import domain.patient.HealthState;
import domain.patient.PatientResidence;
import domain.request.infoRequest.InfoRequestDescription;
import domain.request.serviceRequest.BookingLocation;
import domain.request.serviceRequest.BookingRoute;
import domain.transport.operator.OperatorResidence;
import domain.transport.TransportLocation;
import domain.transport.TransportRoute;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TestDataValue {
    //Ambulance
    public static final int AMBULANCE_NUMBER = 1111;

    //Patient
    public static final String PATIENT_FISCAL_CODE = "patientTest";
    public static final String PATIENT_NAME = "namePatient";
    public static final String PATIENT_SURNAME = "surnamePatient";
    public static final LocalDate PATIENT_BIRTHDAY = LocalDate.of(1970,7,5);
    public static final PatientResidence PATIENT_RESIDENCE = new PatientResidence(new Address("Ferrari"), new HouseNumber(111), new City("Forlì"), new District("FC"), new PostalCode(47722));
    public static final HealthState HEALTH_STATE = new HealthState("Niente da riferire");

    //Booking
    public static final LocalDateTime BOOKING_DATE = LocalDateTime.now();
    public static final BookingRoute BOOKING_ROUTE = new BookingRoute(
                        new BookingLocation(new Address("IV Settembre"),new HouseNumber(12),new City("Cesena"), new District("FC"), new PostalCode(47521)),
            new BookingLocation(new Address("corso cavour"),new HouseNumber(189),new City("Cesena"), new District("FC"), new PostalCode(47521)));

    //ServiceRequest
    public static final LocalDateTime SERVICE_REQUEST_DATE = LocalDateTime.of(2021,5,5,18,0);

    //InfoRequest
    public static final LocalDateTime INFO_REQUEST_DATE = LocalDateTime.of(2021,5,4,18,0);
    public static final InfoRequestDescription INFO_REQUEST_DESCRIPTION = new InfoRequestDescription("Richiesta costi di trasporto da ospedale di Cesena a Residenza in Imola");

    //Operator
    public static final String OPERATOR_ID = "operatorTest";
    public static final String OPERATOR_NAME = "nameOperator";
    public static final String OPERATOR_SURNAME = "surnameOperator";
    public static final LocalDate OPERATOR_BIRTHDAY = LocalDate.of(1990,7,5);
    public static final OperatorResidence OPERATOR_RESIDENCE = new OperatorResidence(new Address("Ferrari"), new HouseNumber(1), new City("Forlì"), new District("FC"), new PostalCode(47122));

    //Transport
    public static final LocalDateTime TRANSPORT_DATE = LocalDateTime.of(2021,8,4,18,0);
    public static final TransportRoute TRANSPORT_ROUTE = new TransportRoute(
            new TransportLocation(new Address("IV Settembre"),new HouseNumber(1),new City("Cesena"), new District("FC"), new PostalCode(47521)),
            new TransportLocation(new Address("corso cavour"),new HouseNumber(19),new City("Cesena"), new District("FC"), new PostalCode(47521)));


}