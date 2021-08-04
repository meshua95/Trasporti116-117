import com.azure.digitaltwins.core.BasicDigitalTwin;
import digitaltwinsapi.Client;
import digitaltwinsapi.GenerateId;
import digitaltwinsapi.CreateRequest;
import digitaltwinsapi.DeleteRequest;
import domain.request.serviceRequest.ServiceRequestId;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DtServiceRequest {
    private final ServiceRequestId id = GenerateId.generateServiceRequestId(TestDataValue.SERVICE_REQUEST_DATE);

    @BeforeClass
    public static void createConnection() {
        Client.getClient();
    }

    @Test
    public void createServiceRequest() {
        CreateRequest.createServiceRequest(TestDataValue.SERVICE_REQUEST_DATE);
        assertEquals(TestDataValue.EQUALS_DT, Client.getClient().getDigitalTwin(id.getId(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);

        DeleteRequest.deleteServiceRequest(id);
    }

}
