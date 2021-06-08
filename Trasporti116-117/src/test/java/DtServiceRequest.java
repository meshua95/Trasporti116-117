import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.implementation.models.ErrorResponseException;
import digitalTwins.Client;
import digitalTwins.request.ServiceRequestDigitalTwin;
import domain.requestBoundedContext.serviceRequest.ServiceRequestId;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class DtServiceRequest {
    private final LocalDateTime dateTime = LocalDateTime.of(2021, 6, 10, 10,30);
    private final ServiceRequestId id = ServiceRequestDigitalTwin.generateServiceRequestId(dateTime);

    @BeforeClass
    public static void createConnection(){
        Client.getClient();
    }

    @Test
    public void createServiceRequest(){
        ServiceRequestDigitalTwin.createServiceRequest(dateTime);
        assertEquals(Client.getClient().getDigitalTwin(id.getserviceRequestId(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);

        ServiceRequestDigitalTwin.deleteServiceRequest(id);
    }

    @Test
    public void deleteInfoRequest(){
        ServiceRequestDigitalTwin.createServiceRequest(dateTime);

        ServiceRequestDigitalTwin.deleteServiceRequest(id);
        try{
            Client.getClient().getDigitalTwin(id.getserviceRequestId(), BasicDigitalTwin.class);
        } catch (Exception ex){
            assertEquals(ex.getClass(), ErrorResponseException.class);
        }
    }
}
