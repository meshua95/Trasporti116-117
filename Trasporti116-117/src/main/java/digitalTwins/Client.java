/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package digitalTwins;

import com.azure.core.http.okhttp.OkHttpAsyncHttpClientBuilder;
import com.azure.digitaltwins.core.DigitalTwinsClient;
import com.azure.digitaltwins.core.DigitalTwinsClientBuilder;
import com.azure.identity.DefaultAzureCredentialBuilder;
import utils.Constants;

public class Client {
    private static DigitalTwinsClient client;

    public static void createClient(){
    // Create client
    client = new DigitalTwinsClientBuilder()
                .credential(
                        new DefaultAzureCredentialBuilder().build()
                )
                        .endpoint(Constants.ENDPOINT)
                .httpClient(new OkHttpAsyncHttpClientBuilder().build())
            .buildClient();
    }

    public static DigitalTwinsClient getClient(){
        return client;
    }
}
