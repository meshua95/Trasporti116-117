package digitalTwins.request;

import com.azure.core.http.rest.PagedIterable;
import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicDigitalTwinMetadata;
import com.azure.digitaltwins.core.BasicRelationship;
import digitalTwins.Client;
import domain.requestBoundedContext.serviceRequest.ServiceRequestId;
import utils.Constants;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ServiceRequestDigitalTwin {
    public static ServiceRequestId createServiceRequest(LocalDateTime dateTime){
        ServiceRequestId serviceRequestId = generateServiceRequestId(dateTime);

        BasicDigitalTwin serviceRequestDT = new BasicDigitalTwin(serviceRequestId.getserviceRequestId())
                .setMetadata(
                        new BasicDigitalTwinMetadata().setModelId(Constants.SERVICE_REQUEST_MODEL_ID)
                )
                .addToContents("dateTime", dateTime);

        BasicDigitalTwin basicTwinResponse = Client.getClient().createOrReplaceDigitalTwin(serviceRequestId.getserviceRequestId(), serviceRequestDT, BasicDigitalTwin.class);
        System.out.println(basicTwinResponse.getId());
        return serviceRequestId;
    }

    public static void deleteServiceRequest(ServiceRequestId serviceRequestId) {
        Client.getClient().listRelationships(serviceRequestId.getserviceRequestId(), BasicRelationship.class)
                .forEach(rel -> Client.getClient().deleteRelationship(serviceRequestId.getserviceRequestId(), rel.getId()));
        Client.getClient().deleteDigitalTwin(serviceRequestId.getserviceRequestId());
    }

    public static ArrayList<ServiceRequestId> getAllServiceRequestId(){
        ArrayList<ServiceRequestId> serviceRequestIds = new ArrayList<>();
        String query = "SELECT $dtId FROM DIGITALTWINS WHERE IS_OF_MODEL('"+ Constants.SERVICE_REQUEST_MODEL_ID + "')";
        PagedIterable<BasicDigitalTwin> pageableResponse = Client.getClient().query(query, BasicDigitalTwin.class);
        pageableResponse.forEach(r-> serviceRequestIds.add(new ServiceRequestId(r.getId())));
        return serviceRequestIds;
    }

    public static ServiceRequestId generateServiceRequestId(LocalDateTime dataOra){
        return new ServiceRequestId("serviceRequest_"+dataOra.toLocalDate() + "_" + dataOra.getHour() + "-" + dataOra.getMinute() + "_");
    }
}
