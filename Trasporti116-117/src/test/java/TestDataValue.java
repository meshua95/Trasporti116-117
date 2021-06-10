import domain.*;
import domain.patientBoundedContext.HealthState;
import domain.patientBoundedContext.PatientResidence;
import domain.requestBoundedContext.infoRequest.InfoRequestDescription;
import domain.requestBoundedContext.serviceRequest.BookingLocation;
import domain.requestBoundedContext.serviceRequest.BookingRoute;
import domain.transportBoundedContext.OperatorResidence;
import domain.transportBoundedContext.TransportLocation;
import domain.transportBoundedContext.TransportRoute;

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
    public static final PatientResidence PATIENT_RESIDENCE = new PatientResidence(new Address("Ferrari"), new HouseNumber("111A"), new City("Forlì"), new District("FC"), new PostalCode(47722));
    public static final HealthState HEALTH_STATE = new HealthState("Niente da riferire");

    //Booking
    public static final LocalDateTime BOOKING_DATE = LocalDateTime.now();
    public static final BookingRoute BOOKING_ROUTE = new BookingRoute(
                        new BookingLocation(new Address("IV Settembre"),new HouseNumber("13B"),new City("Cesena"), new District("FC"), new PostalCode(47521)),
            new BookingLocation(new Address("corso cavour"),new HouseNumber("189C"),new City("Cesena"), new District("FC"), new PostalCode(47521)));

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
    public static final OperatorResidence OPERATOR_RESIDENCE = new OperatorResidence(new Address("Ferrari"), new HouseNumber("1A"), new City("Forlì"), new District("FC"), new PostalCode(47122));

    //Transport
    //public static final LocalDateTime TRANSPORT_DATE = LocalDateTime.now();

}
