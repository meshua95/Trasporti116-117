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
    public void locationTestAddress() {
        assertEquals(TestDataValue.EQUALS_VALUE, TestDataValue.ADDRESS.getAddress(), TestDataValue.ADDRESS_VALUE);
    }

    @Test
    public void locationTestCity() {
        assertEquals(TestDataValue.EQUALS_VALUE, TestDataValue.CITY.getCity(), TestDataValue.CITY_VALUE);
    }

    @Test
    public void locationTestDistrict() {
        assertEquals(TestDataValue.EQUALS_VALUE, TestDataValue.DISTRICT.getDistrict(), TestDataValue.DISTRICT_VALUE);
    }

    @Test
    public void locationTestHousenumber() {
        assertEquals(TestDataValue.EQUALS_VALUE, TestDataValue.HOUSENUMBER.getHouseNumber(), TestDataValue.HOUSENUMBER_VALUE);
    }

    @Test
    public void locationTestPostalCode() {
        assertEquals(TestDataValue.EQUALS_VALUE, TestDataValue.POSTAL_CODE.getPostalCode(), TestDataValue.POSTAL_CODE_VALUE);
    }

    @Test
    public void patientTestAut() {
        Autonomy autonomous = Autonomy.AUTONOMOUS;

        assertEquals(TestDataValue.EQUALS_VALUE, autonomous.getValue(), Autonomy.AUTONOMOUS.getValue());
    }

    @Test
    public void patientTestPartiallyAut() {
        Autonomy partiallyAutonomous = Autonomy.PARTIALLY_AUTONOMOUS;

        assertEquals(TestDataValue.EQUALS_VALUE, partiallyAutonomous.getValue(), Autonomy.PARTIALLY_AUTONOMOUS.getValue());
    }

    @Test
    public void patientTestNotAut() {
        Autonomy notAutonomous = Autonomy.NOT_AUTONOMOUS;

        assertEquals(TestDataValue.EQUALS_VALUE, notAutonomous.getValue(), Autonomy.NOT_AUTONOMOUS.getValue());
    }

    @Test
    public void patientTesthealtDesc() {
        String healtStateDesc = "Nulla da riferire";
        HealthState healthState = new HealthState(healtStateDesc);

        assertEquals(TestDataValue.EQUALS_VALUE, healthState.getDescription(), healtStateDesc);
    }

    @Test
    public void patientTestFC() {
        PatientFiscalCode idPatient = new PatientFiscalCode(TestDataValue.PATIENT_FISCAL_CODE);

        assertEquals(TestDataValue.EQUALS_VALUE, idPatient.getFiscalCode(), TestDataValue.PATIENT_FISCAL_CODE);
    }

    @Test
    public void patientTestBirthday() {
        PatientPersonalData personalData =
                new PatientPersonalData(TestDataValue.PATIENT_NAME,
                        TestDataValue.PATIENT_SURNAME,
                        TestDataValue.PATIENT_BIRTHDAY,
                        TestDataValue.PATIENT_RESIDENCE);

        assertEquals(TestDataValue.EQUALS_VALUE, personalData.getBirthDate(), TestDataValue.PATIENT_BIRTHDAY);
    }

    @Test
    public void patientTestSurname() {
        PatientPersonalData personalData =
                new PatientPersonalData(TestDataValue.PATIENT_NAME,
                        TestDataValue.PATIENT_SURNAME,
                        TestDataValue.PATIENT_BIRTHDAY,
                        TestDataValue.PATIENT_RESIDENCE);

        assertEquals(TestDataValue.EQUALS_VALUE, personalData.getSurname(), TestDataValue.PATIENT_SURNAME);
    }

    @Test
    public void patientTestName() {
        PatientPersonalData personalData =
                new PatientPersonalData(TestDataValue.PATIENT_NAME,
                        TestDataValue.PATIENT_SURNAME,
                        TestDataValue.PATIENT_BIRTHDAY,
                        TestDataValue.PATIENT_RESIDENCE);

        assertEquals(TestDataValue.EQUALS_VALUE, personalData.getName(), TestDataValue.PATIENT_NAME);
    }

    @Test
    public void patientTestAddress() {
        PatientPersonalData personalData =
                new PatientPersonalData(TestDataValue.PATIENT_NAME,
                        TestDataValue.PATIENT_SURNAME,
                        TestDataValue.PATIENT_BIRTHDAY,
                        TestDataValue.PATIENT_RESIDENCE);

        assertEquals(TestDataValue.EQUALS_VALUE, personalData.getResidence().getAddress(), TestDataValue.PATIENT_ADDRESS);
    }

    @Test
    public void patientTestDistrict() {
        PatientPersonalData personalData =
                new PatientPersonalData(TestDataValue.PATIENT_NAME,
                        TestDataValue.PATIENT_SURNAME,
                        TestDataValue.PATIENT_BIRTHDAY,
                        TestDataValue.PATIENT_RESIDENCE);

        assertEquals(TestDataValue.EQUALS_VALUE, personalData.getResidence().getDistrict(), TestDataValue.PATIENT_DISTRICT);
    }

    @Test
    public void patientTestCity() {
        PatientPersonalData personalData =
                new PatientPersonalData(TestDataValue.PATIENT_NAME,
                        TestDataValue.PATIENT_SURNAME,
                        TestDataValue.PATIENT_BIRTHDAY,
                        TestDataValue.PATIENT_RESIDENCE);

        assertEquals(TestDataValue.EQUALS_VALUE, personalData.getResidence().getCity(), TestDataValue.PATIENT_CITY);
    }

    @Test
    public void patientTestHouseNumber() {
        PatientPersonalData personalData =
                new PatientPersonalData(TestDataValue.PATIENT_NAME,
                        TestDataValue.PATIENT_SURNAME,
                        TestDataValue.PATIENT_BIRTHDAY,
                        TestDataValue.PATIENT_RESIDENCE);

        assertEquals(TestDataValue.EQUALS_VALUE, personalData.getResidence().getHouseNumber(), TestDataValue.PATIENT_HOUSENUMBER);
    }

    @Test
    public void patientTestPostalCode() {
        PatientPersonalData personalData =
                new PatientPersonalData(TestDataValue.PATIENT_NAME,
                        TestDataValue.PATIENT_SURNAME,
                        TestDataValue.PATIENT_BIRTHDAY,
                        TestDataValue.PATIENT_RESIDENCE);

        assertEquals(TestDataValue.EQUALS_VALUE, personalData.getResidence().getPostalCode(), TestDataValue.PATIENT_POSTAL_CODE);
    }

    @Test
    public void operatorTestId() {
        OperatorId idOperator = new OperatorId(TestDataValue.OPERATOR_ID);

        assertEquals(TestDataValue.EQUALS_VALUE, idOperator.getId(), TestDataValue.OPERATOR_ID);
    }

    @Test
    public void operatorTestBirthday() {
        OperatorPersonalData personalData =
                new OperatorPersonalData(TestDataValue.OPERATOR_NAME,
                        TestDataValue.OPERATOR_SURNAME,
                        TestDataValue.OPERATOR_BIRTHDAY,
                        TestDataValue.OPERATOR_RESIDENCE);

        assertEquals(TestDataValue.EQUALS_VALUE, personalData.getBirthDate(), TestDataValue.OPERATOR_BIRTHDAY);
    }
    @Test
    public void operatorTestSurname() {
        OperatorPersonalData personalData =
                new OperatorPersonalData(TestDataValue.OPERATOR_NAME,
                        TestDataValue.OPERATOR_SURNAME,
                        TestDataValue.OPERATOR_BIRTHDAY,
                        TestDataValue.OPERATOR_RESIDENCE);

        assertEquals(TestDataValue.EQUALS_VALUE, personalData.getSurname(), TestDataValue.OPERATOR_SURNAME);
    }
    @Test
    public void operatorTestName() {
        OperatorPersonalData personalData =
                new OperatorPersonalData(TestDataValue.OPERATOR_NAME,
                        TestDataValue.OPERATOR_SURNAME,
                        TestDataValue.OPERATOR_BIRTHDAY,
                        TestDataValue.OPERATOR_RESIDENCE);

        assertEquals(TestDataValue.EQUALS_VALUE, personalData.getName(), TestDataValue.OPERATOR_NAME);
    }
    @Test
    public void operatorTestAddress() {
        OperatorPersonalData personalData =
                new OperatorPersonalData(TestDataValue.OPERATOR_NAME,
                        TestDataValue.OPERATOR_SURNAME,
                        TestDataValue.OPERATOR_BIRTHDAY,
                        TestDataValue.OPERATOR_RESIDENCE);

        assertEquals(TestDataValue.EQUALS_VALUE, personalData.getResidence().getAddress(), TestDataValue.OPERATOR_ADDRESS);
    }
    @Test
    public void operatorTestDistrict() {
        OperatorPersonalData personalData =
                new OperatorPersonalData(TestDataValue.OPERATOR_NAME,
                        TestDataValue.OPERATOR_SURNAME,
                        TestDataValue.OPERATOR_BIRTHDAY,
                        TestDataValue.OPERATOR_RESIDENCE);

        assertEquals(TestDataValue.EQUALS_VALUE, personalData.getResidence().getDistrict(), TestDataValue.OPERATOR_DISTRICT);
    }
    @Test
    public void operatorTestCity() {
        OperatorPersonalData personalData =
                new OperatorPersonalData(TestDataValue.OPERATOR_NAME,
                        TestDataValue.OPERATOR_SURNAME,
                        TestDataValue.OPERATOR_BIRTHDAY,
                        TestDataValue.OPERATOR_RESIDENCE);

        assertEquals(TestDataValue.EQUALS_VALUE, personalData.getResidence().getCity(), TestDataValue.OPERATOR_CITY);
    }
    @Test
    public void operatorTestHousenumber() {
        OperatorPersonalData personalData =
                new OperatorPersonalData(TestDataValue.OPERATOR_NAME,
                        TestDataValue.OPERATOR_SURNAME,
                        TestDataValue.OPERATOR_BIRTHDAY,
                        TestDataValue.OPERATOR_RESIDENCE);

        assertEquals(TestDataValue.EQUALS_VALUE, personalData.getResidence().getHouseNumber(), TestDataValue.OPERATOR_HOUSENUMBER);
    }
    @Test
    public void operatorTestPostalCode() {
        OperatorPersonalData personalData =
                new OperatorPersonalData(TestDataValue.OPERATOR_NAME,
                        TestDataValue.OPERATOR_SURNAME,
                        TestDataValue.OPERATOR_BIRTHDAY,
                        TestDataValue.OPERATOR_RESIDENCE);

        assertEquals(TestDataValue.EQUALS_VALUE, personalData.getResidence().getPostalCode(), TestDataValue.OPERATOR_POSTAL_CODE);
    }


    @Test
    public void transportTestDep() {
        TransportRoute transportRoute = new TransportRoute(TestDataValue.DEPARTURE, TestDataValue.DESTINATION);

        assertEquals(TestDataValue.EQUALS_VALUE, transportRoute.getDeparture(), TestDataValue.DEPARTURE);
    }

    @Test
    public void transportTestDes() {
        TransportRoute transportRoute = new TransportRoute(TestDataValue.DEPARTURE, TestDataValue.DESTINATION);

        assertEquals(TestDataValue.EQUALS_VALUE, transportRoute.getDestination(), TestDataValue.DESTINATION);
    }

    @Test
    public void transportTestDepAddress() {
        TransportRoute transportRoute = new TransportRoute(TestDataValue.DEPARTURE, TestDataValue.DESTINATION);

        assertEquals(TestDataValue.EQUALS_VALUE, transportRoute.getDeparture().getAddress(), TestDataValue.DEPARTURE_ADDRESS);
    }

    @Test
    public void transportTestDepDistrict() {
        TransportRoute transportRoute = new TransportRoute(TestDataValue.DEPARTURE, TestDataValue.DESTINATION);

        assertEquals(TestDataValue.EQUALS_VALUE, transportRoute.getDeparture().getDistrict(), TestDataValue.DEPARTURE_DISTRICT);
    }

    @Test
    public void transportTestDepCity() {
        TransportRoute transportRoute = new TransportRoute(TestDataValue.DEPARTURE, TestDataValue.DESTINATION);

        assertEquals(TestDataValue.EQUALS_VALUE, transportRoute.getDeparture().getCity(), TestDataValue.DEPARTURE_CITY);
    }

    @Test
    public void transportTestDepHpusenumber() {
        TransportRoute transportRoute = new TransportRoute(TestDataValue.DEPARTURE, TestDataValue.DESTINATION);

        assertEquals(TestDataValue.EQUALS_VALUE, transportRoute.getDeparture().getHouseNumber(), TestDataValue.DEPARTURE_HOUSENUMBER);
    }

    @Test
    public void transportTestDepPostalCode() {
        TransportRoute transportRoute = new TransportRoute(TestDataValue.DEPARTURE, TestDataValue.DESTINATION);

        assertEquals(TestDataValue.EQUALS_VALUE, transportRoute.getDeparture().getPostalCode(), TestDataValue.DEPARTURE_POSTAL_CODE);
    }

    @Test
    public void transportTestDesAddress() {
        TransportRoute transportRoute = new TransportRoute(TestDataValue.DEPARTURE, TestDataValue.DESTINATION);

        assertEquals(TestDataValue.EQUALS_VALUE, transportRoute.getDestination().getAddress(), TestDataValue.DESTINATION_ADDRESS);
    }

    @Test
    public void transportTestDesDistrict() {
        TransportRoute transportRoute = new TransportRoute(TestDataValue.DEPARTURE, TestDataValue.DESTINATION);

        assertEquals(TestDataValue.EQUALS_VALUE, transportRoute.getDestination().getDistrict(), TestDataValue.DESTINATION_DISTRICT);
    }

    @Test
    public void transportTestDesCity() {
        TransportRoute transportRoute = new TransportRoute(TestDataValue.DEPARTURE, TestDataValue.DESTINATION);

        assertEquals(TestDataValue.EQUALS_VALUE, transportRoute.getDestination().getCity(), TestDataValue.DESTINATION_CITY);
    }

    @Test
    public void transportTestDesHousenumber() {
        TransportRoute transportRoute = new TransportRoute(TestDataValue.DEPARTURE, TestDataValue.DESTINATION);

        assertEquals(TestDataValue.EQUALS_VALUE, transportRoute.getDestination().getHouseNumber(), TestDataValue.DESTINATION_HOUSENUMBER);
    }

    @Test
    public void transportTestDesPostalCode() {
        TransportRoute transportRoute = new TransportRoute(TestDataValue.DEPARTURE, TestDataValue.DESTINATION);

        assertEquals(TestDataValue.EQUALS_VALUE, transportRoute.getDestination().getPostalCode(), TestDataValue.DESTINATION_POSTAL_CODE);
    }

    @Test
    public void bookingTestBookingDeparture() {
        BookingRoute bookingRoute = new BookingRoute(TestDataValue.BOOKING_DEPARTURE, TestDataValue.BOOKING_DESTINATION);

        assertEquals(TestDataValue.EQUALS_VALUE, bookingRoute.getDeparture(), TestDataValue.BOOKING_DEPARTURE);
    }

    @Test
    public void bookingTestBookingDestination() {
        BookingRoute bookingRoute = new BookingRoute(TestDataValue.BOOKING_DEPARTURE, TestDataValue.BOOKING_DESTINATION);

        assertEquals(TestDataValue.EQUALS_VALUE, bookingRoute.getDestination(), TestDataValue.BOOKING_DESTINATION);
    }

    @Test
    public void bookingTestDepAddress() {
        BookingRoute bookingRoute = new BookingRoute(TestDataValue.BOOKING_DEPARTURE, TestDataValue.BOOKING_DESTINATION);

        assertEquals(TestDataValue.EQUALS_VALUE, bookingRoute.getDeparture().getAddress(), TestDataValue.DEPARTURE_ADDRESS);
    }

    @Test
    public void bookingTestDepDistrict() {
        BookingRoute bookingRoute = new BookingRoute(TestDataValue.BOOKING_DEPARTURE, TestDataValue.BOOKING_DESTINATION);

        assertEquals(TestDataValue.EQUALS_VALUE, bookingRoute.getDeparture().getDistrict(), TestDataValue.DEPARTURE_DISTRICT);
    }

    @Test
    public void bookingTestDepCity() {
        BookingRoute bookingRoute = new BookingRoute(TestDataValue.BOOKING_DEPARTURE, TestDataValue.BOOKING_DESTINATION);

        assertEquals(TestDataValue.EQUALS_VALUE, bookingRoute.getDeparture().getCity(), TestDataValue.DEPARTURE_CITY);
    }


    @Test
    public void bookingTestDepHouseNumber() {
        BookingRoute bookingRoute = new BookingRoute(TestDataValue.BOOKING_DEPARTURE, TestDataValue.BOOKING_DESTINATION);

        assertEquals(TestDataValue.EQUALS_VALUE, bookingRoute.getDeparture().getHouseNumber(), TestDataValue.DEPARTURE_HOUSENUMBER);
    }


    @Test
    public void bookingTestDepPostalCode() {
        BookingRoute bookingRoute = new BookingRoute(TestDataValue.BOOKING_DEPARTURE, TestDataValue.BOOKING_DESTINATION);

        assertEquals(TestDataValue.EQUALS_VALUE, bookingRoute.getDeparture().getPostalCode(), TestDataValue.DEPARTURE_POSTAL_CODE);
    }

    @Test
    public void bookingTestDesAddress() {
        BookingRoute bookingRoute = new BookingRoute(TestDataValue.BOOKING_DEPARTURE, TestDataValue.BOOKING_DESTINATION);

        assertEquals(TestDataValue.EQUALS_VALUE, bookingRoute.getDestination().getAddress(), TestDataValue.DESTINATION_ADDRESS);
    }

    @Test
    public void bookingTestDesDistrict() {
        BookingRoute bookingRoute = new BookingRoute(TestDataValue.BOOKING_DEPARTURE, TestDataValue.BOOKING_DESTINATION);

        assertEquals(TestDataValue.EQUALS_VALUE, bookingRoute.getDestination().getDistrict(), TestDataValue.DESTINATION_DISTRICT);
    }

    @Test
    public void bookingTestDesCity() {
        BookingRoute bookingRoute = new BookingRoute(TestDataValue.BOOKING_DEPARTURE, TestDataValue.BOOKING_DESTINATION);

        assertEquals(TestDataValue.EQUALS_VALUE, bookingRoute.getDestination().getCity(), TestDataValue.DESTINATION_CITY);
    }

    @Test
    public void bookingTestDesHousenumber() {
        BookingRoute bookingRoute = new BookingRoute(TestDataValue.BOOKING_DEPARTURE, TestDataValue.BOOKING_DESTINATION);

        assertEquals(TestDataValue.EQUALS_VALUE, bookingRoute.getDestination().getHouseNumber(), TestDataValue.DESTINATION_HOUSENUMBER);
    }

    @Test
    public void bookingTestDesPostalCode() {
        BookingRoute bookingRoute = new BookingRoute(TestDataValue.BOOKING_DEPARTURE, TestDataValue.BOOKING_DESTINATION);

        assertEquals(TestDataValue.EQUALS_VALUE, bookingRoute.getDestination().getPostalCode(), TestDataValue.DESTINATION_POSTAL_CODE);
    }


}
