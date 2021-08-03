/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */


import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicRelationship;
import com.azure.digitaltwins.core.implementation.models.ErrorResponseException;

import digitaltwinsapi.Client;
import digitaltwinsapi.CreateAmbulance;
import digitaltwinsapi.DeleteAmbulance;
import digitaltwinsapi.GetGPSCoordinates;
import domain.transport.ambulance.AmbulanceId;
import domain.transport.ambulance.Coordinates;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.errorCode.DeleteDigitalTwinStatusCode;
import utils.errorCode.QueryTimeOutException;

import static org.junit.Assert.assertEquals;

public class DtAmbulance {

    private final AmbulanceId ambulanceId = new AmbulanceId(TestDataValue.AMBULANCE_NUMBER);

    @BeforeClass
    public static void createConnection() {
        Client.getClient();
    }

    //Quando si crea un'ambulanza, in automatico si deve creare anche il relativo GPS e la relazione che li collega
    @Test
    public void createAmbulanceWithAmbulanceId() {
        CreateAmbulance.createAmbulance(ambulanceId);

        assertEquals(TestDataValue.EQUALS_DT, Client.getClient().getDigitalTwin(this.ambulanceId.getId(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);

        DeleteAmbulance.deleteAmbulance(ambulanceId);
    }

    @Test
    public void createGPSWithAmbulanceId() {
        CreateAmbulance.createAmbulance(ambulanceId);

        assertEquals(TestDataValue.EQUALS_DT, Client.getClient().getDigitalTwin(this.ambulanceId.getGpsId(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);

        DeleteAmbulance.deleteAmbulance(ambulanceId);
    }


    @Test
    public void createAmbulanceGpsRelWithAmbulanceId() {
        CreateAmbulance.createAmbulance(ambulanceId);

        assertEquals(TestDataValue.EQUALS_REL, Client.getClient().getRelationship(ambulanceId.getId(), ambulanceId.getId() + "to" + ambulanceId.getGpsId(), BasicRelationship.class).getClass(), BasicRelationship.class);

        DeleteAmbulance.deleteAmbulance(ambulanceId);
    }


    @Test
    public void createAmbulance() {
        CreateAmbulance.createAmbulance(TestDataValue.AMBULANCE_NUMBER);

        assertEquals(TestDataValue.EQUALS_DT, Client.getClient().getDigitalTwin(new AmbulanceId(TestDataValue.AMBULANCE_NUMBER).getId(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);

        DeleteAmbulance.deleteAmbulance(ambulanceId);
    }

    @Test
    public void createGPS() {
        CreateAmbulance.createAmbulance(TestDataValue.AMBULANCE_NUMBER);

        assertEquals(TestDataValue.EQUALS_DT, Client.getClient().getDigitalTwin(new AmbulanceId(TestDataValue.AMBULANCE_NUMBER).getGpsId(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);

        DeleteAmbulance.deleteAmbulance(ambulanceId);
    }

    @Test
    public void createAmbulanceGpsRel() {
        CreateAmbulance.createAmbulance(TestDataValue.AMBULANCE_NUMBER);

        assertEquals(TestDataValue.EQUALS_REL, Client.getClient().getRelationship(new AmbulanceId(TestDataValue.AMBULANCE_NUMBER).getId(), new AmbulanceId(TestDataValue.AMBULANCE_NUMBER).getId() + "to" + new AmbulanceId(TestDataValue.AMBULANCE_NUMBER).getGpsId(), BasicRelationship.class).getClass(), BasicRelationship.class);

        DeleteAmbulance.deleteAmbulance(ambulanceId);
    }

    @Test
    public void deleteAmbulance() {
        CreateAmbulance.createAmbulance(TestDataValue.AMBULANCE_NUMBER);

        assertEquals(TestDataValue.DELETED, DeleteAmbulance.deleteAmbulance(ambulanceId), DeleteDigitalTwinStatusCode.DELETED);
    }

    @Test
    public void ambulanceNotExistIfDeleted() {
        CreateAmbulance.createAmbulance(TestDataValue.AMBULANCE_NUMBER);

        DeleteAmbulance.deleteAmbulance(ambulanceId);
        try {
            Client.getClient().getDigitalTwin(ambulanceId.getId(), BasicDigitalTwin.class);
        } catch (Exception ex) {
            assertEquals(TestDataValue.NOT_EXIST, ex.getClass(), ErrorResponseException.class);
        }
    }

    @Test
    public void gpsNotExistIfDeleted() {
        CreateAmbulance.createAmbulance(TestDataValue.AMBULANCE_NUMBER);

        DeleteAmbulance.deleteAmbulance(ambulanceId);

        try {
            Client.getClient().getDigitalTwin(ambulanceId.getGpsId(), BasicDigitalTwin.class);
        } catch (Exception ex) {
            assertEquals(TestDataValue.NOT_EXIST, ex.getClass(), ErrorResponseException.class);
        }
    }

    @Test
    public void getAmbulancePositionLat() throws QueryTimeOutException {
        CreateAmbulance.createAmbulance(ambulanceId);
        Coordinates standardCoordinates = new Coordinates(0.0, 0.0);

        assertEquals(TestDataValue.EQUALS_VALUE, standardCoordinates.getLatitude(), GetGPSCoordinates.getGPSCoordinatesOfAmbulance(ambulanceId).getLatitude(), 0.0);

        DeleteAmbulance.deleteAmbulance(ambulanceId);
    }

    @Test
    public void getAmbulancePositionLon() throws QueryTimeOutException {
        CreateAmbulance.createAmbulance(ambulanceId);
        Coordinates standardCoordinates = new Coordinates(0.0, 0.0);

        assertEquals(TestDataValue.EQUALS_VALUE, standardCoordinates.getLongitude(), GetGPSCoordinates.getGPSCoordinatesOfAmbulance(ambulanceId).getLongitude(), 0.0);

        DeleteAmbulance.deleteAmbulance(ambulanceId);
    }

}
