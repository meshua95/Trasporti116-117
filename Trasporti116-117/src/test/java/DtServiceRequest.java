import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.implementation.models.ErrorResponseException;
import digitalTwins.Client;
import digitalTwins.request.ServiceRequestDigitalTwin;
import domain.requestBoundedContext.serviceRequest.ServiceRequestId;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DtServiceRequest {
    private final ServiceRequestId id = ServiceRequestDigitalTwin.generateServiceRequestId(TestDataValue.SERVICE_REQUEST_DATE);

    @BeforeClass
    public static void createConnection(){
        Client.getClient();
    }

    @Test
    public void createServiceRequest(){
        ServiceRequestDigitalTwin.createServiceRequest(TestDataValue.SERVICE_REQUEST_DATE);
        assertEquals(Client.getClient().getDigitalTwin(id.getserviceRequestId(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);

        ServiceRequestDigitalTwin.deleteServiceRequest(id);
    }

    @Test
    public void deleteInfoRequest(){
        ServiceRequestDigitalTwin.createServiceRequest(TestDataValue.SERVICE_REQUEST_DATE);

        ServiceRequestDigitalTwin.deleteServiceRequest(id);
        try{
            Client.getClient().getDigitalTwin(id.getserviceRequestId(), BasicDigitalTwin.class);
        } catch (Exception ex){
            assertEquals(ex.getClass(), ErrorResponseException.class);
        }
    }
}
