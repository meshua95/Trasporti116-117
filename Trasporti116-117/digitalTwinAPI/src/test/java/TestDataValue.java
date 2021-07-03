import domain.*;
import domain.patient.HealthState;
import domain.patient.PatientResidence;
import domain.request.infoRequest.InfoRequestDescription;
import domain.request.serviceRequest.BookingLocation;
import domain.request.serviceRequest.BookingRoute;
import domain.transport.operator.OperatorResidence;
import domain.transport.TransportLocation;
import domain.transport.TransportRoute;
import org.spongycastle.asn1.crmf.POPOSigningKey;

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
    public static final Address PATIENT_ADDRESS = new Address("Ferrari");
    public static final HouseNumber PATIENT_HOUSENUMBER = new HouseNumber(111);
    public static final City PATIENT_CITY = new City("Forlì");
    public static final District PATIENT_DISTRICT = new District("FC");
    public static final PostalCode PATIENT_POSTAL_CODE = new PostalCode(47722);

    public static final PatientResidence PATIENT_RESIDENCE = new PatientResidence(PATIENT_ADDRESS, PATIENT_HOUSENUMBER, PATIENT_CITY, PATIENT_DISTRICT, PATIENT_POSTAL_CODE);
    public static final HealthState HEALTH_STATE = new HealthState("Niente da riferire");

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
    public static final Address OPERATOR_ADDRESS = new Address("Corso Cavour");
    public static final HouseNumber OPERATOR_HOUSENUMBER = new HouseNumber(1);
    public static final City OPERATOR_CITY = new City("Cesena");
    public static final District OPERATOR_DISTRICT = new District("FC");
    public static final PostalCode OPERATOR_POSTAL_CODE = new PostalCode(47521);
    public static final OperatorResidence OPERATOR_RESIDENCE = new OperatorResidence(OPERATOR_ADDRESS, OPERATOR_HOUSENUMBER, OPERATOR_CITY, OPERATOR_DISTRICT,OPERATOR_POSTAL_CODE);

    //Transport
    public static final Address DEPARTURE_ADDRESS = new Address("IV Settembre");
    public static final HouseNumber DEPARTURE_HOUSENUMBER = new HouseNumber(1);
    public static final City DEPARTURE_CITY = new City("Cesena");
    public static final District DEPARTURE_DISTRICT = new District("FC");
    public static final PostalCode DEPARTURE_POSTAL_CODE = new PostalCode(47521);
    public static final Address DESTINATION_ADDRESS = new Address("Giovanni Bovio");
    public static final HouseNumber DESTINATION_HOUSENUMBER = new HouseNumber(122);
    public static final City DESTINATION_CITY = new City("Cesena");
    public static final District DESTINATION_DISTRICT = new District("FC");
    public static final PostalCode DESTINATION_POSTAL_CODE = new PostalCode(47521);
    public static final TransportLocation DEPARTURE = new TransportLocation(DEPARTURE_ADDRESS, DEPARTURE_HOUSENUMBER, DEPARTURE_CITY, DEPARTURE_DISTRICT, DEPARTURE_POSTAL_CODE);
    public static final TransportLocation DESTINATION = new TransportLocation(DESTINATION_ADDRESS, DESTINATION_HOUSENUMBER, DESTINATION_CITY, DESTINATION_DISTRICT, DESTINATION_POSTAL_CODE);

    //Booking
    public static final LocalDateTime BOOKING_DATE = LocalDateTime.now();
    public static final BookingLocation BOOKING_DEPARTURE = new BookingLocation(DEPARTURE_ADDRESS, DEPARTURE_HOUSENUMBER, DEPARTURE_CITY, DEPARTURE_DISTRICT, DEPARTURE_POSTAL_CODE);
    public static final BookingLocation BOOKING_DESTINATION = new BookingLocation(DESTINATION_ADDRESS, DESTINATION_HOUSENUMBER, DESTINATION_CITY, DESTINATION_DISTRICT, DESTINATION_POSTAL_CODE);
    public static final BookingRoute BOOKING_ROUTE = new BookingRoute(BOOKING_DEPARTURE, BOOKING_DESTINATION);

    //General location
    public static final String ADDRESS_VALUE = "Rossi";
    public static final int HOUSENUMBER_VALUE = 123;
    public static final String CITY_VALUE = "Cesena";
    public static final String DISTRICT_VALUE = "FC";
    public static final int POSTAL_CODE_VALUE = 47521;

    public static final Address ADDRESS = new Address(ADDRESS_VALUE);
    public static final HouseNumber HOUSENUMBER = new HouseNumber(HOUSENUMBER_VALUE);
    public static final City CITY = new City(CITY_VALUE);
    public static final District DISTRICT = new District(DISTRICT_VALUE);
    public static final PostalCode POSTAL_CODE = new PostalCode(POSTAL_CODE_VALUE);
}
