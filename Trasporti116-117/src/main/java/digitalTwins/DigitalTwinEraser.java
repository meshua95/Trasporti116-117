/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package digitalTwins;

import com.azure.digitaltwins.core.BasicRelationship;

import java.util.List;

public class DigitalTwinEraser {
    public static void deleteTwins(List<String> dtId) {
        dtId.forEach(id -> {
            Client.getClient().listRelationships(id, BasicRelationship.class)
                    .forEach(rel -> Client.getClient().deleteRelationship(id, rel.getId()));
            Client.getClient().deleteDigitalTwin(id);
        });
    }
}
