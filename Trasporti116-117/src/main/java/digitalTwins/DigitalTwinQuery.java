package digitalTwins;

import com.azure.core.http.rest.PagedIterable;
import com.azure.digitaltwins.core.BasicDigitalTwin;
import domain.ambulanza.IdAmbulanza;
import domain.operatore.IdOperatore;
import domain.paziente.IdPaziente;
import utils.Constants;

import java.util.ArrayList;

public class DigitalTwinQuery {

    public static ArrayList<IdPaziente> getAllPazienteIdTwins(){
        ArrayList<IdPaziente> pazientiIds = new ArrayList<>();
        String query = "SELECT $dtId FROM DIGITALTWINS T WHERE T.$metadata.$model = '"+ Constants.PAZIENTE_ID + "'";
        PagedIterable<BasicDigitalTwin> pageableResponse = Client.getClient().query(query, BasicDigitalTwin.class);
        pageableResponse.forEach(r-> pazientiIds.add(new IdPaziente(r.getId())));
        return pazientiIds;
    }

    public static ArrayList<IdOperatore> getAllOperatoreIdTwins(){
        ArrayList<IdOperatore> operatoriIds = new ArrayList<>();
        String query = "SELECT $dtId FROM DIGITALTWINS T WHERE T.$metadata.$model = '"+ Constants.OPERATORE_AMBULANZA_ID + "'";
        PagedIterable<BasicDigitalTwin> pageableResponse = Client.getClient().query(query, BasicDigitalTwin.class);
        pageableResponse.forEach(r-> operatoriIds.add(new IdOperatore(r.getId())));
        return operatoriIds;
    }

    public static ArrayList<IdAmbulanza> getAllAmbulanzaIdTwins(){
        ArrayList<IdAmbulanza> ambulanzeIds = new ArrayList<>();
        String query = "SELECT $dtId FROM DIGITALTWINS T WHERE T.$metadata.$model = '"+ Constants.AMBULANZA_MODEL_ID + "'";
        PagedIterable<BasicDigitalTwin> pageableResponse = Client.getClient().query(query, BasicDigitalTwin.class);
        pageableResponse.forEach(r-> ambulanzeIds.add(new IdAmbulanza(r.getId())));
        return ambulanzeIds;
    }
}
