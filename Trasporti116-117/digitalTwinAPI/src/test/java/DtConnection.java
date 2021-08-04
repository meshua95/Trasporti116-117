/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

import com.azure.digitaltwins.core.DigitalTwinsClient;
import digitaltwinsapi.Client;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DtConnection {

    @Test
    public void testConnection() {
        assertEquals(TestDataValue.EQUALS_VALUE, Client.getClient().getClass(), DigitalTwinsClient.class);
    }
}
