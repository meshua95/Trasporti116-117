/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */


import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicRelationship;
import com.azure.digitaltwins.core.implementation.models.ErrorResponseException;
import digitalTwinsAPI.*;
import domain.transport.ambulance.AmbulanceId;
import domain.transport.ambulance.Coordinates;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.errorCode.DeleteDigitalTwinStatusCode;
import utils.errorCode.QueryTimeOutException;

import static org.junit.Assert.*;

public class DtAmbulance {

    private final AmbulanceId ambulanceId = new AmbulanceId(TestDataValue.AMBULANCE_NUMBER);

    @BeforeClass
    public static void createConnection(){
        Client.getClient();
    }

    //Quando si crea un'ambulanza, in automatico si deve creare anche il relativo GPS e la relazione che li collega
    @Test
    public void createAmbulanceWithAmbulanceId(){
        CreateAmbulance.createAmbulance(ambulanceId);

        assertEquals(Client.getClient().getDigitalTwin(this.ambulanceId.getAmbulanceId(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);
        assertEquals(Client.getClient().getDigitalTwin(this.ambulanceId.getGpsId(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);
        assertEquals(Client.getClient().getRelationship(ambulanceId.getAmbulanceId(), ambulanceId.getAmbulanceId() + "to" + ambulanceId.getGpsId(), BasicRelationship.class).getClass(), BasicRelationship.class);

        DeleteAmbulance.deleteAmbulance(ambulanceId);
    }

    @Test
    public void createAmbulanceWithAmbulanceNumber(){
        CreateAmbulance.createAmbulance(TestDataValue.AMBULANCE_NUMBER);

        assertEquals(Client.getClient().getDigitalTwin(new AmbulanceId(TestDataValue.AMBULANCE_NUMBER).getAmbulanceId(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);
        assertEquals(Client.getClient().getDigitalTwin(new AmbulanceId(TestDataValue.AMBULANCE_NUMBER).getGpsId(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);
        assertEquals(Client.getClient().getRelationship(new AmbulanceId(TestDataValue.AMBULANCE_NUMBER).getAmbulanceId(), new AmbulanceId(TestDataValue.AMBULANCE_NUMBER).getAmbulanceId() + "to" + new AmbulanceId(TestDataValue.AMBULANCE_NUMBER).getGpsId(), BasicRelationship.class).getClass(), BasicRelationship.class);

        DeleteAmbulance.deleteAmbulance(ambulanceId);
    }

    @Test
    public void deleteAmbulanceWithNoRelationship(){
        AmbulanceId ambulanceId = new AmbulanceId(TestDataValue.AMBULANCE_NUMBER);
        CreateAmbulance.createAmbulance(TestDataValue.AMBULANCE_NUMBER);

        assertEquals(DeleteAmbulance.deleteAmbulance(ambulanceId), DeleteDigitalTwinStatusCode.DELETED);
        try {
            Client.getClient().getDigitalTwin(ambulanceId.getAmbulanceId(), BasicDigitalTwin.class);
        } catch (Exception ex){
            assertEquals(ex.getClass(), ErrorResponseException.class);
        }
        try {
            Client.getClient().getDigitalTwin(ambulanceId.getGpsId(), BasicDigitalTwin.class);
        } catch (Exception ex){
            assertEquals(ex.getClass(), ErrorResponseException.class);
        }
    }

    @Test
    public void getAmbulance() throws QueryTimeOutException {
        CreateAmbulance.createAmbulance(ambulanceId);

        assertTrue(GetAmbulance.getAllAmbulanceIdTwins().stream().anyMatch(a-> a.getAmbulanceId().equals(ambulanceId.getAmbulanceId())));

        DeleteAmbulance.deleteAmbulance(ambulanceId);
    }

    @Test
    public void getAmbulancePosition() throws QueryTimeOutException {
        CreateAmbulance.createAmbulance(ambulanceId);
        Coordinates standardCoordinates = new Coordinates(0.0,0.0);

        assertEquals(standardCoordinates.getLatitude(), GetGPSCoordinates.getGPSCoordinatesOfAmbulance(ambulanceId).getLatitude(), 0.0);
        assertEquals(standardCoordinates.getLongitude(), GetGPSCoordinates.getGPSCoordinatesOfAmbulance(ambulanceId).getLongitude(), 0.0);

        DeleteAmbulance.deleteAmbulance(ambulanceId);
    }

}