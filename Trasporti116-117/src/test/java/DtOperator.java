/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.implementation.models.ErrorResponseException;
import digitalTwins.Client;
import model.*;
import domain.operatore.OperatorDigitalTwin;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class DtOperator {
    private final OperatorId idOperatore = new OperatorId("operatore1");

    @BeforeClass
    public static void createConnection(){
        Client.getClient();
    }

    @Test
    public void createOperatore(){

        PersonalData personalData =
                new PersonalData("Mario",
                        "Rossi",
                        LocalDate.of(1988, 1,8),
                        new Location(new Address("IV Settembre"),new HouseNumber("13B"),new City("Cesena"), new District("FC"), new PostalCode("47521")));

        OperatorDigitalTwin.createOperatore(idOperatore, personalData);
        assertEquals(Client.getClient().getDigitalTwin(idOperatore.getOperatorId(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);
    }

    @Test
    public void deleteOperatore(){
        try{
            OperatorDigitalTwin.deleteOperatore(idOperatore);
        } catch (Exception ex){
            assertEquals(ex.getClass(), ErrorResponseException.class);
        }
    }

}
