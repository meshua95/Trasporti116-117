/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.implementation.models.ErrorResponseException;
import digitalTwins.Client;
import domain.*;
import domain.transportBoundedContext.*;
import digitalTwins.operator.OperatorDigitalTwin;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class DtOperator {
    private final OperatorId idOperator = new OperatorId("OP00");
    private final OperatorPersonalData personalData =
            new OperatorPersonalData("Mario",
                    "Rossi",
                    LocalDate.of(1988, 1,8),
                    new OperatorResidence(new Address("IV Settembre"),new HouseNumber("13B"),new City("Cesena"), new District("FC"), new PostalCode(47521)));

    @BeforeClass
    public static void createConnection(){
        Client.getClient();
    }

    @Test
    public void createOperator(){
        OperatorDigitalTwin.createOperator(idOperator, personalData);
        assertEquals(Client.getClient().getDigitalTwin(idOperator.getOperatorId(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);

        OperatorDigitalTwin.deleteOperatore(idOperator);
    }

    @Test
    public void deleteOperator(){
        OperatorDigitalTwin.createOperator(idOperator, personalData);

        OperatorDigitalTwin.deleteOperatore(idOperator);
        try{
            Client.getClient().getDigitalTwin(idOperator.getOperatorId(), BasicDigitalTwin.class);
        } catch (Exception ex){
            assertEquals(ex.getClass(), ErrorResponseException.class);
        }
    }

}
