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

    private final LocalDateTime dateTime = LocalDateTime.of(2021, 6, 10, 10,30);
    private final InfoRequestDescription description = new InfoRequestDescription("Richiesta costi di trasporto da ospedale di Cesena a Residenza in Imola");
    private final InfoRequestId id = InfoRequestDigitalTwin.generateInfoRequestId(dateTime);
    @BeforeClass
    public static void createConnection(){
        Client.getClient();
    }

    @Test
    public void createInfoRequest(){
        InfoRequestDigitalTwin.createInfoRequest(dateTime,description);
        assertEquals(Client.getClient().getDigitalTwin(id.getInfoRequestId(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);

        InfoRequestDigitalTwin.deleteInfoRequest(id);
    }

    @Test
    public void deleteInfoRequest(){
        InfoRequestDigitalTwin.createInfoRequest(dateTime,description);

        InfoRequestDigitalTwin.deleteInfoRequest(id);
        try{
            Client.getClient().getDigitalTwin(id.getInfoRequestId(), BasicDigitalTwin.class);
        } catch (Exception ex){
            assertEquals(ex.getClass(), ErrorResponseException.class);
        }
    }

}
