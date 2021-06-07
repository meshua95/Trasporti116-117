/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

import domain.ambulanceBoundedContext.AmbulanceId;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AmbulanceIdGeneration {
    private final int ambulanceNumber = 2;

    @Test
    public void createAmbulanceIdByAmbulanceNumber(){
        AmbulanceId ambulanceId = new AmbulanceId(ambulanceNumber);
        assertEquals(ambulanceId.getAmbulanceId(), "ambulanza-"+ambulanceNumber);
    }

    @Test
    public void createGpsIdByAmbulanceNumber(){
        AmbulanceId ambulanceId = new AmbulanceId(ambulanceNumber);
        assertEquals(ambulanceId.getGpsId(), "gps-"+ambulanceNumber);
    }

    @Test
    public void createGpsIdByAmbulanceId(){
        AmbulanceId ambulanceId = new AmbulanceId("ambulanza-"+ambulanceNumber);
        assertEquals(ambulanceId.getGpsId(), "gps-"+ambulanceNumber);
    }

    @Test
    public void checkGetAmbulanceNumber(){
        AmbulanceId ambulanceId = new AmbulanceId("ambulanza-"+ambulanceNumber);
        assertEquals(ambulanceId.getAmbulanceNumber(), ambulanceNumber);
    }

}
