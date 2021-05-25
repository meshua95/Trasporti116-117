/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.implementation.models.ErrorResponseException;
import digitalTwins.Client;
import digitalTwins.DigitalTwinEraser;
import digitalTwins.DigitalTwinsBuilder;
import domain.operatore.DatiAnagraficiOperatore;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class DtOperatore {
    private final String idOperatore = "RSSMRA88A08E730X";

    @BeforeClass
    public static void createConnection(){
        Client.createClient();
    }

    @Test
    public void createOperatore(){

        DatiAnagraficiOperatore datiAnagrafici =
                new DatiAnagraficiOperatore("Mario",
                        "Rossi",
                        LocalDate.of(1988, 1,8),
                        new DatiAnagraficiOperatore.Residenza("settembre","13B","Cesena", "FC", 47521));

        DigitalTwinsBuilder.createOperatoreAmbulanzaDigitalTwin(idOperatore, datiAnagrafici);
        assertEquals(Client.getClient().getDigitalTwin(idOperatore, BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);
    }

    @Test
    public void deleteOperatore(){
        try{
            DigitalTwinEraser.deleteTwins(Arrays.asList(idOperatore));
        } catch (Exception ex){
            assertEquals(ex.getClass(), ErrorResponseException.class);
        }
    }

}
