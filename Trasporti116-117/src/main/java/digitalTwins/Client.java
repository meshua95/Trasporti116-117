/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package digitalTwins;

import com.azure.digitaltwins.core.DigitalTwinsAsyncClient;
import com.azure.digitaltwins.core.DigitalTwinsClient;
import com.azure.digitaltwins.core.DigitalTwinsClientBuilder;
import com.azure.identity.ClientSecretCredentialBuilder;
import utils.Constants;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    public static ExecutorService executor = Executors.newSingleThreadExecutor();
    private static DigitalTwinsClient client = null;

    private static void createClient(){
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

    public static DigitalTwinsClient getClient(){
        if (client == null) {
            createClient();
        }
        return client;
    }

    private static DigitalTwinsAsyncClient clientAsync = null;

    private static void createClientAsync(){
        // Create client connection
        clientAsync = new DigitalTwinsClientBuilder()
                .credential(
                        new ClientSecretCredentialBuilder()
                                .tenantId(Constants.TENANT_ID)
                                .clientId(Constants.CLIENT_ID)
                                .clientSecret(Constants.CLIENT_SECRET)
                                .build()
                )
                .endpoint(Constants.ENDPOINT)
                .buildAsyncClient();
    }

    public static DigitalTwinsAsyncClient getClientAsync(){
        if (clientAsync == null) {
            createClientAsync();
        }
        return clientAsync;
    }
}
