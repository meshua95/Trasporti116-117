/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

import domain.transport.ambulance.AmbulanceId;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AmbulanceIdGeneration {

    @Test
    public void createAmbulanceIdByAmbulanceNumber(){
        AmbulanceId ambulanceId = new AmbulanceId(TestDataValue.AMBULANCE_NUMBER);
        assertEquals(ambulanceId.getAmbulanceId(), "ambulanza-"+TestDataValue.AMBULANCE_NUMBER);
    }

    @Test
    public void createGpsIdByAmbulanceNumber(){
        AmbulanceId ambulanceId = new AmbulanceId(TestDataValue.AMBULANCE_NUMBER);
        assertEquals(ambulanceId.getGpsId(), "gps-"+TestDataValue.AMBULANCE_NUMBER);
    }

    @Test
    public void createGpsIdByAmbulanceId(){
        AmbulanceId ambulanceId = new AmbulanceId("ambulanza-"+TestDataValue.AMBULANCE_NUMBER);
        assertEquals(ambulanceId.getGpsId(), "gps-"+TestDataValue.AMBULANCE_NUMBER);
    }

    @Test
    public void checkGetAmbulanceNumber(){
        AmbulanceId ambulanceId = new AmbulanceId("ambulanza-"+TestDataValue.AMBULANCE_NUMBER);
        assertEquals(ambulanceId.getAmbulanceNumber(), TestDataValue.AMBULANCE_NUMBER);
    }

}
