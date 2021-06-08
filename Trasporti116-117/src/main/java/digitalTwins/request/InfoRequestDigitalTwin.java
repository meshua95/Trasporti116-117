package digitalTwins.request;

import com.azure.core.http.rest.PagedIterable;
import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicDigitalTwinMetadata;
import com.azure.digitaltwins.core.BasicRelationship;
import digitalTwins.Client;
import domain.patientBoundedContext.PatientFiscalCode;
import domain.requestBoundedContext.infoRequest.InfoRequestDescription;
import domain.requestBoundedContext.infoRequest.InfoRequestId;
import domain.requestBoundedContext.serviceRequest.BookingTransportId;
import utils.Constants;

import java.time.LocalDateTime;
import java.util.ArrayList;

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

    public static ArrayList<InfoRequestId> getAllInfoRequestId(){
        ArrayList<InfoRequestId> infoRequestIds = new ArrayList<>();
        String query = "SELECT $dtId FROM DIGITALTWINS WHERE IS_OF_MODEL('"+ Constants.INFO_REQUEST_MODEL_ID + "')";
        PagedIterable<BasicDigitalTwin> pageableResponse = Client.getClient().query(query, BasicDigitalTwin.class);
        pageableResponse.forEach(r-> infoRequestIds.add(new InfoRequestId(r.getId())));
        return infoRequestIds;
    }

    public static InfoRequestId generateInfoRequestId(LocalDateTime dataOra){
        return new InfoRequestId("infoRequest_"+dataOra.toLocalDate() + "_" + dataOra.getHour() + "-" + dataOra.getMinute() + "_");
    }
}
