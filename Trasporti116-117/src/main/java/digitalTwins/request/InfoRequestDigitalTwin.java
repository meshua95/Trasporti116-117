package digitalTwins.request;

import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicDigitalTwinMetadata;
import com.azure.digitaltwins.core.BasicRelationship;
import digitalTwins.Client;
import domain.patientBoundedContext.PatientFiscalCode;
import domain.requestBoundedContext.infoRequest.InfoRequestDescription;
import domain.requestBoundedContext.infoRequest.InfoRequestId;
import utils.Constants;

import java.time.LocalDateTime;

public class InfoRequestDigitalTwin {
    public static InfoRequestId createInfoRequest(LocalDateTime dateTime, InfoRequestDescription description){
        InfoRequestId infoRequestId = generateInfoRequestId(dateTime);

        BasicDigitalTwin infoRequestDT = new BasicDigitalTwin(infoRequestId.getInfoRequestId())
                .setMetadata(
                        new BasicDigitalTwinMetadata().setModelId(Constants.INFO_REQUEST_MODEL_ID)
                )
                .addToContents("dateTime", dateTime)
                .addToContents("description", description.getDescription());

        BasicDigitalTwin basicTwinResponse = Client.getClient().createOrReplaceDigitalTwin(infoRequestId.getInfoRequestId(), infoRequestDT, BasicDigitalTwin.class);
        System.out.println(basicTwinResponse.getId());
        return infoRequestId;
    }

    public static void deleteInfoRequest(InfoRequestId infoRequestId) {
        Client.getClient().listRelationships(infoRequestId.getInfoRequestId(), BasicRelationship.class)
                .forEach(rel -> Client.getClient().deleteRelationship(infoRequestId.getInfoRequestId(), rel.getId()));
        Client.getClient().deleteDigitalTwin(infoRequestId.getInfoRequestId());
    }

    public static InfoRequestId generateInfoRequestId(LocalDateTime dataOra){
        return new InfoRequestId("infoRequest_"+dataOra.toLocalDate() + "_" + dataOra.getHour() + "-" + dataOra.getMinute() + "_");
    }
}
