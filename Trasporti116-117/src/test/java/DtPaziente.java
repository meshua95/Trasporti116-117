/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.implementation.models.ErrorResponseException;
import digitalTwins.Client;
import digitalTwins.DigitalTwinEraser;
import digitalTwins.DigitalTwinsBuilder;
import domain.paziente.Autonomia;
import domain.paziente.DatiAnagraficiPaziente;
import domain.paziente.StatoDiSalute;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class DtPaziente {

    private final String idPaziente = "CRGMHI12M21E730X";

    @BeforeClass
    public static void createConnection(){
        Client.getClient();
    }

    @Test
    public void createPaziente(){

        DatiAnagraficiPaziente datiAnagrafici =
                new DatiAnagraficiPaziente("Mario",
                        "Rossi",
                        LocalDate.of(1988, 1,8),
                        new DatiAnagraficiPaziente.Residenza("settembre","13B","Cesena", "FC", 47521));
        StatoDiSalute statoSalute = new StatoDiSalute("Niente da riferire");

        DigitalTwinsBuilder.createPazienteDigitalTwin(idPaziente, datiAnagrafici, statoSalute, Autonomia.PARZIALMENTE_AUTONOMO);
        assertEquals(Client.getClient().getDigitalTwin(idPaziente, BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);
    }

    @Test
    public void deletePaziente(){
        try{
            DigitalTwinEraser.deleteTwins(Arrays.asList(idPaziente));
        } catch (Exception ex){
            assertEquals(ex.getClass(), ErrorResponseException.class);
        }
    }

}
