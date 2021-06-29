/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

import com.azure.digitaltwins.core.BasicDigitalTwin;
import digitalTwinsAPI.*;
import domain.patient.*;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.errorCode.QueryTimeOutException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DtPatient {

    private final PatientFiscalCode idPatient = new PatientFiscalCode(TestDataValue.PATIENT_FISCAL_CODE);
    private final PatientPersonalData personalData =
            new PatientPersonalData(TestDataValue.PATIENT_NAME,
                    TestDataValue.PATIENT_SURNAME,
                    TestDataValue.PATIENT_BIRTHDAY,
                    TestDataValue.PATIENT_RESIDENCE);

    @BeforeClass
    public static void createConnection(){
        Client.getClient();
    }

    @Test
    public void createPatient(){

        CreatePatient.createPatient(idPatient, personalData, TestDataValue.HEALTH_STATE, Autonomy.PARTIALLY_AUTONOMOUS);
        assertEquals(Client.getClient().getDigitalTwin(idPatient.getFiscalCode(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);

        DeletePatient.deletePatient(idPatient);
    }

    @Test
    public void getPatient() throws QueryTimeOutException {
        CreatePatient.createPatient(idPatient, personalData, TestDataValue.HEALTH_STATE, Autonomy.PARTIALLY_AUTONOMOUS);

        assertTrue(GetPatient.getAllPatientId().stream().anyMatch(a-> a.getFiscalCode().equals(idPatient.getFiscalCode())));

        DeletePatient.deletePatient(idPatient);
    }
}
