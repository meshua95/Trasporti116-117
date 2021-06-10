/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.implementation.models.ErrorResponseException;
import digitalTwins.Client;
import domain.transportBoundedContext.*;
import digitalTwins.operator.OperatorDigitalTwin;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DtOperator {
    private final OperatorId idOperator = new OperatorId(TestDataValue.OPERATOR_ID);
    private final OperatorPersonalData personalData =
            new OperatorPersonalData(TestDataValue.OPERATOR_NAME,
                    TestDataValue.OPERATOR_SURNAME,
                    TestDataValue.OPERATOR_BIRTHDAY,
                    TestDataValue.OPERATOR_RESIDENCE);
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
