import domain.patient.Autonomy;
import domain.patient.HealthState;
import domain.patient.PatientFiscalCode;
import domain.patient.PatientPersonalData;
import domain.request.serviceRequest.BookingRoute;
import domain.transport.TransportRoute;
import domain.transport.operator.OperatorId;
import domain.transport.operator.OperatorPersonalData;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DomainTest {

    @Test
    public void locationTest(){
        assertEquals(TestDataValue.ADDRESS.getAddress(),TestDataValue.ADDRESS_VALUE);
        assertEquals(TestDataValue.CITY.getCity(),TestDataValue.CITY_VALUE);
        assertEquals(TestDataValue.DISTRICT.getDistrict(),TestDataValue.DISTRICT_VALUE);
        assertEquals(TestDataValue.HOUSENUMBER.getHouseNumber(),TestDataValue.HOUSENUMBER_VALUE);
        assertEquals(TestDataValue.POSTAL_CODE.getPostalCode(),TestDataValue.POSTAL_CODE_VALUE);
    }

    @Test
    public void patientTest(){
        PatientFiscalCode idPatient = new PatientFiscalCode(TestDataValue.PATIENT_FISCAL_CODE);
        PatientPersonalData personalData =
                new PatientPersonalData(TestDataValue.PATIENT_NAME,
                        TestDataValue.PATIENT_SURNAME,
                        TestDataValue.PATIENT_BIRTHDAY,
                        TestDataValue.PATIENT_RESIDENCE);
        String healtStateDesc = "Nulla da riferire";
        HealthState healthState = new HealthState(healtStateDesc);
        Autonomy autonomous = Autonomy.AUTONOMOUS;
        Autonomy partiallyAutonomous = Autonomy.PARTIALLY_AUTONOMOUS;
        Autonomy notAutonomous = Autonomy.NOT_AUTONOMOUS;

        assertEquals(autonomous.getValue(), Autonomy.AUTONOMOUS.getValue());
        assertEquals(partiallyAutonomous.getValue(), Autonomy.PARTIALLY_AUTONOMOUS.getValue());
        assertEquals(notAutonomous.getValue(), Autonomy.NOT_AUTONOMOUS.getValue());
        assertEquals(healthState.getDescription(),healtStateDesc);
        assertEquals(idPatient.getFiscalCode(),TestDataValue.PATIENT_FISCAL_CODE);
        assertEquals(personalData.getBirthDate(),TestDataValue.PATIENT_BIRTHDAY);
        assertEquals(personalData.getSurname(),TestDataValue.PATIENT_SURNAME);
        assertEquals(personalData.getName(),TestDataValue.PATIENT_NAME);
        assertEquals(personalData.getResidence().getAddress(),TestDataValue.PATIENT_ADDRESS);
        assertEquals(personalData.getResidence().getDistrict(), TestDataValue.PATIENT_DISTRICT);
        assertEquals(personalData.getResidence().getCity(), TestDataValue.PATIENT_CITY);
        assertEquals(personalData.getResidence().getHouseNumber(), TestDataValue.PATIENT_HOUSENUMBER);
        assertEquals(personalData.getResidence().getPostalCode(), TestDataValue.PATIENT_POSTAL_CODE);
    }

    @Test
    public void operatorTest(){
        OperatorId idOperator = new OperatorId(TestDataValue.OPERATOR_ID);
        OperatorPersonalData personalData =
                new OperatorPersonalData(TestDataValue.OPERATOR_NAME,
                        TestDataValue.OPERATOR_SURNAME,
                        TestDataValue.OPERATOR_BIRTHDAY,
                        TestDataValue.OPERATOR_RESIDENCE);

        assertEquals(idOperator.getOperatorId(),TestDataValue.OPERATOR_ID);
        assertEquals(personalData.getBirthDate(),TestDataValue.OPERATOR_BIRTHDAY);
        assertEquals(personalData.getSurname(),TestDataValue.OPERATOR_SURNAME);
        assertEquals(personalData.getName(),TestDataValue.OPERATOR_NAME);
        assertEquals(personalData.getResidence().getAddress(),TestDataValue.OPERATOR_ADDRESS);
        assertEquals(personalData.getResidence().getDistrict(), TestDataValue.OPERATOR_DISTRICT);
        assertEquals(personalData.getResidence().getCity(), TestDataValue.OPERATOR_CITY);
        assertEquals(personalData.getResidence().getHouseNumber(), TestDataValue.OPERATOR_HOUSENUMBER);
        assertEquals(personalData.getResidence().getPostalCode(), TestDataValue.OPERATOR_POSTAL_CODE);
    }

    @Test
    public void transportTest(){
        TransportRoute transportRoute = new TransportRoute(TestDataValue.DEPARTURE, TestDataValue.DESTINATION);

        assertEquals(transportRoute.getDeparture(),TestDataValue.DEPARTURE);
        assertEquals(transportRoute.getDestination(),TestDataValue.DESTINATION);
        assertEquals(transportRoute.getDeparture().getAddress(),TestDataValue.DEPARTURE_ADDRESS);
        assertEquals(transportRoute.getDeparture().getDistrict(), TestDataValue.DEPARTURE_DISTRICT);
        assertEquals(transportRoute.getDeparture().getCity(), TestDataValue.DEPARTURE_CITY);
        assertEquals(transportRoute.getDeparture().getHouseNumber(), TestDataValue.DEPARTURE_HOUSENUMBER);
        assertEquals(transportRoute.getDeparture().getPostalCode(), TestDataValue.DEPARTURE_POSTAL_CODE);
        assertEquals(transportRoute.getDestination().getAddress(),TestDataValue.DESTINATION_ADDRESS);
        assertEquals(transportRoute.getDestination().getDistrict(), TestDataValue.DESTINATION_DISTRICT);
        assertEquals(transportRoute.getDestination().getCity(), TestDataValue.DESTINATION_CITY);
        assertEquals(transportRoute.getDestination().getHouseNumber(), TestDataValue.DESTINATION_HOUSENUMBER);
        assertEquals(transportRoute.getDestination().getPostalCode(), TestDataValue.DESTINATION_POSTAL_CODE);

    }

    @Test
    public void bookingTest(){
        BookingRoute bookingRoute = new BookingRoute(TestDataValue.BOOKING_DEPARTURE, TestDataValue.BOOKING_DESTINATION);

        assertEquals(bookingRoute.getDeparture(),TestDataValue.BOOKING_DEPARTURE);
        assertEquals(bookingRoute.getDestination(),TestDataValue.BOOKING_DESTINATION);
        assertEquals(bookingRoute.getDeparture().getAddress(),TestDataValue.DEPARTURE_ADDRESS);
        assertEquals(bookingRoute.getDeparture().getDistrict(), TestDataValue.DEPARTURE_DISTRICT);
        assertEquals(bookingRoute.getDeparture().getCity(), TestDataValue.DEPARTURE_CITY);
        assertEquals(bookingRoute.getDeparture().getHouseNumber(), TestDataValue.DEPARTURE_HOUSENUMBER);
        assertEquals(bookingRoute.getDeparture().getPostalCode(), TestDataValue.DEPARTURE_POSTAL_CODE);
        assertEquals(bookingRoute.getDestination().getAddress(),TestDataValue.DESTINATION_ADDRESS);
        assertEquals(bookingRoute.getDestination().getDistrict(), TestDataValue.DESTINATION_DISTRICT);
        assertEquals(bookingRoute.getDestination().getCity(), TestDataValue.DESTINATION_CITY);
        assertEquals(bookingRoute.getDestination().getHouseNumber(), TestDataValue.DESTINATION_HOUSENUMBER);
        assertEquals(bookingRoute.getDestination().getPostalCode(), TestDataValue.DESTINATION_POSTAL_CODE);

    }
}
