/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.implementation.models.ErrorResponseException;
import digitalTwins.Client;
import domain.patientBoundedContext.*;
import model.*;
import digitalTwins.patient.PatientDigitalTwin;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class DtPatient {

    private final PatientFiscalCode idPatient = new PatientFiscalCode("paziente0");
    private final PatientPersonalData personalData =
            new PatientPersonalData("Mario",
                    "Rossi",
                    LocalDate.of(1988, 1,8),
                    new PatientResidence(new PatientAddress("IV Settembre"),new PatientHouseNumber("13B"),new PatientCity("Cesena"), new PatientDistrict("FC"), new PatientPostalCode(47521)));
    private final HealthState healthState = new HealthState("Niente da riferire");


    @BeforeClass
    public static void createConnection(){
        Client.getClient();
    }

    @Test
    public void createPaziente(){

        PatientDigitalTwin.createPatient(idPatient, personalData, healthState, Autonomy.PARTIALLY_AUTONOMOUS);
        assertEquals(Client.getClient().getDigitalTwin(idPatient.getFiscalCode(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);

        PatientDigitalTwin.deletePatient(idPatient);
    }

    @Test
    public void deletePaziente(){
        PatientDigitalTwin.createPatient(idPatient, personalData, healthState, Autonomy.PARTIALLY_AUTONOMOUS);

        PatientDigitalTwin.deletePatient(idPatient);
        try{
            Client.getClient().getDigitalTwin(idPatient.getFiscalCode(), BasicDigitalTwin.class);
        } catch (Exception ex){
            assertEquals(ex.getClass(), ErrorResponseException.class);
        }
    }

}
