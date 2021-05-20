/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package digitalTwins;

import com.azure.digitaltwins.core.BasicRelationship;
import domain.paziente.Autonomia;
import domain.paziente.DatiAnagraficiPaziente;
import domain.paziente.StatoDiSalute;
import domain.trasporto.Itinerario;
import domain.ambulanza.StatoAmbulanza;
import domain.trasporto.StatoTrasporto;
import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicDigitalTwinComponent;
import com.azure.digitaltwins.core.BasicDigitalTwinMetadata;
import domain.trasporto.DatiAnagraficiOperatore;
import utils.Constants;

import java.time.LocalDateTime;

public class DigitalTwinsBuilder {

    public static void createAmbulanzaDigitalTwin(String dtId, StatoAmbulanza stato, int numeroAmbulanza){
        BasicDigitalTwin ambulanzaDT = new BasicDigitalTwin(dtId)
                .setMetadata(
                        new BasicDigitalTwinMetadata().setModelId(Constants.AMBULANZA_MODEL_ID)
                )
                .addToContents("number", numeroAmbulanza)
                .addToContents(
                        "GPS",

                        new BasicDigitalTwinComponent()
                )
                .addToContents("stato", stato.getValue());

        BasicDigitalTwin basicTwinResponse = Client.getClient().createOrReplaceDigitalTwin(dtId, ambulanzaDT, BasicDigitalTwin.class);
        System.out.println(basicTwinResponse.getId());
    }

    public static void createPazienteDigitalTwin(String dtId, DatiAnagraficiPaziente datiAnagraficiPaziente, StatoDiSalute statoDiSalute, Autonomia autonomia){
        BasicDigitalTwin pazienteDT = new BasicDigitalTwin(dtId)
                .setMetadata(
                        new BasicDigitalTwinMetadata().setModelId(Constants.PAZIENTE_ID)
                )
                .addToContents("datiAnagrafici", datiAnagraficiPaziente)
                .addToContents("statoDiSalute", statoDiSalute)
                .addToContents("autonomia", autonomia.getValue());

        BasicDigitalTwin basicTwinResponse = Client.getClient().createOrReplaceDigitalTwin(dtId, pazienteDT, BasicDigitalTwin.class);
        System.out.println(basicTwinResponse.getId());
    }

    public static void createOperatoreAmbulanzaDigitalTwin(String dtId, DatiAnagraficiOperatore datiAnagrafici){
        BasicDigitalTwin OperatoreAmbulanzaDT = new BasicDigitalTwin(dtId)
                .setMetadata(
                        new BasicDigitalTwinMetadata().setModelId(Constants.OPERATORE_AMBULANZA_ID)
                )
                .addToContents("datiAnagrafici", datiAnagrafici);

        BasicDigitalTwin basicTwinResponse = Client.getClient().createOrReplaceDigitalTwin(dtId, OperatoreAmbulanzaDT, BasicDigitalTwin.class);
        System.out.println(basicTwinResponse.getId());

    }

    public static void createTrasportoDigitalTwin(String dtId, LocalDateTime dataOra, StatoTrasporto stato, Itinerario itinerario, String ambulanzaId, String pazienteId, String operatoreId){
       //create digital twin "trasporto"
        BasicDigitalTwin trasportoDT = new BasicDigitalTwin(dtId)
                .setMetadata(
                        new BasicDigitalTwinMetadata().setModelId(Constants.TRASPORTO_ID)
                )
                .addToContents("dataOra", dataOra)
                .addToContents("itinerario", itinerario)
                .addToContents("stato", stato.getValue());

        BasicDigitalTwin basicTwinResponse = Client.getClient().createOrReplaceDigitalTwin(dtId, trasportoDT, BasicDigitalTwin.class);
        System.out.println(basicTwinResponse.getId());

        //add relationship whit ambulanza
        createdTrasportoRelationship(dtId, ambulanzaId, "usa");

        //add relationship whit paziente

        createdTrasportoRelationship(dtId, pazienteId, "trasporta");

        //add relationship whit operatore
        createdTrasportoRelationship(dtId, operatoreId, "guidata");
    }

    private static void createdTrasportoRelationship(String trasportoId, String targetId, String relationshipName){
        BasicRelationship trasportotoAmbulanzaRelationship =
                new BasicRelationship(
                        trasportoId + "to" + targetId,
                        trasportoId,
                        targetId,
                        relationshipName);

        BasicRelationship createdRelationship = Client.getClient().createOrReplaceRelationship(
                trasportoId,
                trasportoId + "to" + targetId,
                trasportotoAmbulanzaRelationship,
                BasicRelationship.class);
    }


}
