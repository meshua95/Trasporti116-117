/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package digitaltwinsapi;

import com.azure.digitaltwins.core.DigitalTwinsClient;
import com.azure.digitaltwins.core.DigitalTwinsClientBuilder;
import com.azure.identity.ClientSecretCredentialBuilder;
import utils.Constants;

/**
 * Reppresent the client for use Azure API
 */
public final class Client {
    private Client() { }
    private static DigitalTwinsClient client = null;

    private static void createClient() {
    // Create client connection
        client = new DigitalTwinsClientBuilder()
                .credential(
                        new ClientSecretCredentialBuilder()
                                .tenantId(Constants.TENANT_ID)
                                .clientId(Constants.CLIENT_ID)
                                .clientSecret(Constants.CLIENT_SECRET)
                                .build()
                )
                .endpoint(Constants.ENDPOINT)
                .buildClient();
    }

    /**
     * @return the sync client
     */
    public static DigitalTwinsClient getClient() {
        if (client == null) {
            createClient();
        }
        return client;
    }

}
