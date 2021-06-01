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

public class DtPaziente {

    private final FiscalCode idPaziente = new FiscalCode("paziente1");

    @BeforeClass
    public static void createConnection(){
        Client.getClient();
    }

    @Test
    public void createPaziente(){

        PersonalData datiAnagrafici =
                new PersonalData("Mario",
                        "Rossi",
                        LocalDate.of(1988, 1,8),
                        new Location(new Address("IV Settembre"),new HouseNumber("13B"),new City("Cesena"), new District("FC"), new PostalCode("47521")));
        HealthState statoSalute = new HealthState("Niente da riferire");

        PatientDigitalTwin.createPatient(idPaziente, datiAnagrafici, statoSalute, Autonomy.PARTIALLY_AUTONOMOUS);
        assertEquals(Client.getClient().getDigitalTwin(idPaziente.getFiscalCode(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);
    }

    @Test
    public void deletePaziente(){
        try{
            PatientDigitalTwin.deletePatient(idPaziente);
        } catch (Exception ex){
            assertEquals(ex.getClass(), ErrorResponseException.class);
        }
    }

}
