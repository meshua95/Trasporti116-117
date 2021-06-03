/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.implementation.models.ErrorResponseException;
import digitalTwins.Client;
import model.*;
import domain.paziente.PatientDigitalTwin;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class DtPatient {

    private final FiscalCode idPatient = new FiscalCode("paziente0");
    private final PersonalData personalData =
            new PersonalData("Mario",
                    "Rossi",
                    LocalDate.of(1988, 1,8),
                    new Location(new Address("IV Settembre"),new HouseNumber("13B"),new City("Cesena"), new District("FC"), new PostalCode(47521)));
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