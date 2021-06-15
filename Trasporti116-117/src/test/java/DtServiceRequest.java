import com.azure.digitaltwins.core.BasicDigitalTwin;
import digitalTwinsAPI.Client;
import digitalTwinsAPI.GenerateId;
import digitalTwinsAPI.CreateRequest;
import digitalTwinsAPI.DeleteRequest;
import domain.request.serviceRequest.ServiceRequestId;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DtServiceRequest {
    private final ServiceRequestId id = GenerateId.generateServiceRequestId(TestDataValue.SERVICE_REQUEST_DATE);

    @BeforeClass
    public static void createConnection(){
        Client.getClient();
    }

    @Test
    public void createServiceRequest(){
        CreateRequest.createServiceRequest(TestDataValue.SERVICE_REQUEST_DATE);
        assertEquals(Client.getClient().getDigitalTwin(id.getserviceRequestId(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);

        DeleteRequest.deleteServiceRequest(id);
    }

}
