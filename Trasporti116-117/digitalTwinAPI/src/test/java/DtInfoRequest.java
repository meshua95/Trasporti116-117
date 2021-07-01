import com.azure.digitaltwins.core.BasicDigitalTwin;
import digitalTwinsAPI.Client;
import digitalTwinsAPI.GenerateId;
import digitalTwinsAPI.CreateRequest;
import digitalTwinsAPI.DeleteRequest;
import domain.request.infoRequest.InfoRequestId;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DtInfoRequest {
    private final InfoRequestId id = GenerateId.generateInfoRequestId(TestDataValue.INFO_REQUEST_DATE);

    @BeforeClass
    public static void createConnection(){
        Client.getClient();
    }

    @Test
    public void createInfoRequest(){
        CreateRequest.createInfoRequest(TestDataValue.INFO_REQUEST_DATE, TestDataValue.INFO_REQUEST_DESCRIPTION);
        assertEquals(Client.getClient().getDigitalTwin(id.getInfoRequestId(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);

        DeleteRequest.deleteInfoRequest(id);
    }

}
