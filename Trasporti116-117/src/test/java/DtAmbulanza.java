/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */


import com.azure.digitaltwins.core.BasicDigitalTwin;
import digitalTwins.Client;
import digitalTwins.DigitalTwinsBuilder;
import domain.ambulanza.StatoAmbulanza;
import org.junit.BeforeClass;
import org.junit.Test;

public class DtAmbulanza {

    @BeforeClass
    public static void createConnection(){
        Client.createClient();
    }

    @Test
    public void createAmbulanza(){
        int idAmbulanza = 3;
        DigitalTwinsBuilder.createAmbulanzaDigitalTwin(StatoAmbulanza.PRONTA, idAmbulanza);
        Client.getClient().getDigitalTwin("ambulanza3", BasicDigitalTwin.class);
    }

}
