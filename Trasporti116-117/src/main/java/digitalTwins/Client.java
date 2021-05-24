/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package digitalTwins;

import com.azure.core.http.okhttp.OkHttpAsyncHttpClientBuilder;
import com.azure.digitaltwins.core.DigitalTwinsClient;
import com.azure.digitaltwins.core.DigitalTwinsClientBuilder;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.azure.identity.DefaultAzureCredentialBuilder;
import utils.Constants;

public class Client {
    private static DigitalTwinsClient client;

    public static void createClient(){
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
                .httpClient(new OkHttpAsyncHttpClientBuilder().build())
                .buildClient();
<<<<<<< HEAD

=======
>>>>>>> fed18a8be888729a6c380aa00a76223b7f055a03
    }

    public static DigitalTwinsClient getClient(){
        return client;
    }
}
