/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */


import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.implementation.models.ErrorResponseException;
import digitalTwins.Client;
import domain.ambulanza.AmbulanceDigitalTwin;
import model.AmbulanceId;
import model.AmbulanceState;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DtAmbulanza {

    private final int idAmbulanza = 2;

    @BeforeClass
    public static void createConnection(){
        Client.getClient();
    }

    @Test
    public void createAmbulanza(){
        AmbulanceDigitalTwin.createAmbulance(AmbulanceState.READY, idAmbulanza);
        assertEquals(Client.getClient().getDigitalTwin("ambulanza" + idAmbulanza, BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);
    }

    @Test
    public void deleteAmbulanza(){
        try{
            AmbulanceDigitalTwin.deleteAmbulance(new AmbulanceId(idAmbulanza));
        } catch (Exception ex){
            assertEquals(ex.getClass(), ErrorResponseException.class);
        }
    }
}
