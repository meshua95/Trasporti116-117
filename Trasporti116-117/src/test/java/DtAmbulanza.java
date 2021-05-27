/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */


import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.implementation.models.ErrorResponseException;
import digitalTwins.Client;
import domain.ambulanza.AmbulanzaDigitalTwin;
import model.StatoAmbulanza;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class DtAmbulanza {

    private final int idAmbulanza = 3;

    @BeforeClass
    public static void createConnection(){
        Client.getClient();
    }

    @Test
    public void createAmbulanza(){

        AmbulanzaDigitalTwin.createAmbulanza(StatoAmbulanza.PRONTA, idAmbulanza);
        assertEquals(Client.getClient().getDigitalTwin("ambulanza" + idAmbulanza, BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);
    }

    @Test
    public void deleteAmbulanza(){
        try{
            AmbulanzaDigitalTwin.deleteAmbulanza(Arrays.asList("ambulanza" + idAmbulanza));
        } catch (Exception ex){
            assertEquals(ex.getClass(), ErrorResponseException.class);
        }
    }

}
