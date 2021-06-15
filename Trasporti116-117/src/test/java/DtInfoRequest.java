import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.implementation.models.ErrorResponseException;
import digitalTwins.Client;
import digitalTwins.request.InfoRequestDigitalTwin;
import domain.requestBoundedContext.infoRequest.InfoRequestDescription;
import domain.requestBoundedContext.infoRequest.InfoRequestId;
import org.junit.BeforeClass;
import org.junit.Test;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class DtInfoRequest {
    private final InfoRequestId id = InfoRequestDigitalTwin.generateInfoRequestId(TestDataValue.INFO_REQUEST_DATE);

    @BeforeClass
    public static void createConnection(){
        Client.getClient();
    }

    @Test
    public void createInfoRequest(){
        InfoRequestDigitalTwin.createInfoRequest(TestDataValue.INFO_REQUEST_DATE, TestDataValue.INFO_REQUEST_DESCRIPTION);
        assertEquals(Client.getClient().getDigitalTwin(id.getInfoRequestId(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);

        InfoRequestDigitalTwin.deleteInfoRequest(id);
    }

}
