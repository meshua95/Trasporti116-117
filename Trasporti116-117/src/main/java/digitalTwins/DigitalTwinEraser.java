/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package digitalTwins;

import com.azure.digitaltwins.core.BasicRelationship;

import java.util.List;

public class DigitalTwinEraser {

    public static void deleteTwin(String dtId) {
        Client.getClient().listRelationships(dtId, BasicRelationship.class).forEach(r->System.out.println(r.getId()));
        Client.getClient().listRelationships(dtId, BasicRelationship.class)
                .forEach(rel -> {
                    System.out.println("rel.getId()");
                    System.out.println(rel.getId());
                    Client.getClient().deleteRelationship(dtId, rel.getId());
                });

        Client.getClient().deleteDigitalTwin(dtId);
    }

    public static void deleteTwins(List<String> dtId) {
        dtId.forEach(id -> deleteTwin(id));
    }
}
