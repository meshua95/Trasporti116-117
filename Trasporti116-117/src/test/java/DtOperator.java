/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

import com.azure.digitaltwins.core.BasicDigitalTwin;
import digitalTwinsAPI.Client;
import digitalTwinsAPI.CreateOperator;
import digitalTwinsAPI.DeleteOperator;
import digitalTwinsAPI.GetOperator;
import domain.transport.operator.OperatorId;
import domain.transport.operator.OperatorPersonalData;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.errorCode.QueryTimeOutException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        CreateOperator.createOperator(idOperator, personalData);
        assertEquals(Client.getClient().getDigitalTwin(idOperator.getOperatorId(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);

        DeleteOperator.deleteOperator(idOperator);
    }

    @Test
    public void getOperator() throws QueryTimeOutException {
        CreateOperator.createOperator(idOperator, personalData);

        assertTrue(GetOperator.getAllOperatorId().stream().anyMatch(a-> a.getOperatorId().equals(idOperator.getOperatorId())));

        DeleteOperator.deleteOperator(idOperator);
    }

}
