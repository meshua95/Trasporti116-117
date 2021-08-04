import domain.DTDLAddress;
import domain.DTDLCity;
import domain.DTDLHouseNumber;
import domain.DTDLDistrict;
import domain.DTDLPostalCode;

import domain.patient.HealthState;
import domain.patient.PatientResidence;
import domain.request.infoRequest.InfoRequestDescription;
import domain.request.serviceRequest.BookingLocation;
import domain.request.serviceRequest.BookingRoute;
import domain.transport.operator.OperatorResidence;
import domain.transport.TransportLocation;

import java.time.LocalDate;
import java.time.LocalDateTime;

public final class TestDataValue {

    private TestDataValue() { }
    //Ambulance
    public static final int AMBULANCE_NUMBER = 1111;

    //Patient
    public static final String PATIENT_FISCAL_CODE = "patientTest";
    public static final String PATIENT_NAME = "namePatient";
    public static final String PATIENT_SURNAME = "surnamePatient";
    public static final LocalDate PATIENT_BIRTHDAY = LocalDate.of(1970, 7, 5);
    public static final DTDLAddress PATIENT_ADDRESS = new DTDLAddress("Ferrari");
    public static final DTDLHouseNumber PATIENT_HOUSENUMBER = new DTDLHouseNumber(111);
    public static final DTDLCity PATIENT_CITY = new DTDLCity("Forlì");
    public static final DTDLDistrict PATIENT_DISTRICT = new DTDLDistrict("FC");
    public static final DTDLPostalCode PATIENT_POSTAL_CODE = new DTDLPostalCode(47722);

    public static final PatientResidence PATIENT_RESIDENCE = new PatientResidence(PATIENT_ADDRESS, PATIENT_HOUSENUMBER, PATIENT_CITY, PATIENT_DISTRICT, PATIENT_POSTAL_CODE);
    public static final HealthState HEALTH_STATE = new HealthState("Niente da riferire");

    //ServiceRequest
    public static final LocalDateTime SERVICE_REQUEST_DATE = LocalDateTime.of(2021, 5, 5, 18, 0);

    //InfoRequest
    public static final LocalDateTime INFO_REQUEST_DATE = LocalDateTime.of(2021, 5, 4, 18, 0);
    public static final InfoRequestDescription INFO_REQUEST_DESCRIPTION = new InfoRequestDescription("Richiesta costi di trasporto da ospedale di Cesena a Residenza in Imola");

    //Operator
    public static final String OPERATOR_ID = "operatorTest";
    public static final String OPERATOR_NAME = "nameOperator";
    public static final String OPERATOR_SURNAME = "surnameOperator";
    public static final LocalDate OPERATOR_BIRTHDAY = LocalDate.of(1990, 7, 5);
    public static final DTDLAddress OPERATOR_ADDRESS = new DTDLAddress("Corso Cavour");
    public static final DTDLHouseNumber OPERATOR_HOUSENUMBER = new DTDLHouseNumber(1);
    public static final DTDLCity OPERATOR_CITY = new DTDLCity("Gambettola");
    public static final DTDLDistrict OPERATOR_DISTRICT = new DTDLDistrict("FC");
    public static final DTDLPostalCode OPERATOR_POSTAL_CODE = new DTDLPostalCode(47521);
    public static final OperatorResidence OPERATOR_RESIDENCE = new OperatorResidence(OPERATOR_ADDRESS, OPERATOR_HOUSENUMBER, OPERATOR_CITY, OPERATOR_DISTRICT, OPERATOR_POSTAL_CODE);

    //Transport
    public static final DTDLAddress DEPARTURE_ADDRESS = new DTDLAddress("IV Settembre");
    public static final DTDLHouseNumber DEPARTURE_HOUSENUMBER = new DTDLHouseNumber(1);
    public static final DTDLCity DEPARTURE_CITY = new DTDLCity("Cesena");
    public static final DTDLDistrict DEPARTURE_DISTRICT = new DTDLDistrict("FC");
    public static final DTDLPostalCode DEPARTURE_POSTAL_CODE = new DTDLPostalCode(47521);
    public static final DTDLAddress DESTINATION_ADDRESS = new DTDLAddress("Giovanni Bovio");
    public static final DTDLHouseNumber DESTINATION_HOUSENUMBER = new DTDLHouseNumber(122);
    public static final DTDLCity DESTINATION_CITY = new DTDLCity("Ravenna");
    public static final DTDLDistrict DESTINATION_DISTRICT = new DTDLDistrict("FC");
    public static final DTDLPostalCode DESTINATION_POSTAL_CODE = new DTDLPostalCode(47521);
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
    public static final String CITY_VALUE = "Forlì";
    public static final String DISTRICT_VALUE = "FC";
    public static final int POSTAL_CODE_VALUE = 47521;

    public static final DTDLAddress ADDRESS = new DTDLAddress(ADDRESS_VALUE);
    public static final DTDLHouseNumber HOUSENUMBER = new DTDLHouseNumber(HOUSENUMBER_VALUE);
    public static final DTDLCity CITY = new DTDLCity(CITY_VALUE);
    public static final DTDLDistrict DISTRICT = new DTDLDistrict(DISTRICT_VALUE);
    public static final DTDLPostalCode POSTAL_CODE = new DTDLPostalCode(POSTAL_CODE_VALUE);

    //Assert messagge
    public static final String NOT_EQUALS_VALUE = "recovered value is not equal to the past one";
    public static final String EQUALS_VALUE = "recovered value is equal to the correct one";
    public static final String EQUALS_DT = "recovered digital twin is equal to the correct one";
    public static final String EQUALS_REL = "recovered digital twin relationship is equal to the correct one";
    public static final String DELETED = "deleted with success";
    public static final String NOT_EXIST = "the digital twin correctly does not exist";
}
