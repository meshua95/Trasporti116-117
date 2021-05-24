/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

import digitalTwins.Client;
import org.junit.Test;
import static org.junit.Assert.*;


public class DtConnection{

    @Test
    public void testConnection(){
        Client.createClient();
        assertNotNull(Client.getClient());
    }
}
