/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

import com.azure.digitaltwins.core.BasicDigitalTwin;
import digitaltwinsapi.Client;
import digitaltwinsapi.CreateOperator;
import digitaltwinsapi.DeleteOperator;
import domain.transport.operator.OperatorId;
import domain.transport.operator.OperatorPersonalData;
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
    public static void createConnection() {
        Client.getClient();
    }

    @Test
    public void createOperator() {
        CreateOperator.createOperator(idOperator, personalData);
        assertEquals(TestDataValue.EQUALS_DT, Client.getClient().getDigitalTwin(idOperator.getId(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);

        DeleteOperator.deleteOperator(idOperator);
    }
}
