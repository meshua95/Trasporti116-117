/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */
package digitalTwinsAPI;

import com.azure.core.http.rest.PagedIterable;
import com.azure.digitaltwins.core.BasicDigitalTwin;
import domain.patient.PatientFiscalCode;
import domain.request.serviceRequest.BookingTransportId;
import org.json.simple.JSONObject;
import utils.Constants;
import utils.errorCode.QueryTimeOutException;

import java.util.ArrayList;

public class GetPatient {

    /**
     * Get the patient for specific booking
     *
     * @param  bookingId booking of which you want the patient
     * @return Fiscal Code of the patient
     * @throws QueryTimeOutException
     */
    public static PatientFiscalCode getPatientIdByBookingId(BookingTransportId bookingId) throws QueryTimeOutException {
        String query = "SELECT target.$dtId " +
                "FROM DIGITALTWINS source " +
                "JOIN target RELATED source.transport " +
                "WHERE source.$dtId = '"+ bookingId.getId() +"'";

        PagedIterable<JSONObject> pageableResponse = Client.getClient().query(query, JSONObject.class);
        long startTime = System.currentTimeMillis();

        while(pageableResponse.stream().findFirst().isEmpty()){
            if (System.currentTimeMillis() - startTime > QueryTimeOutException.TIME_OUT)
                throw new QueryTimeOutException();
        }

        return new PatientFiscalCode(pageableResponse.stream().findFirst().get().get("$dtId").toString());
    }

    /**
     * Get all patients
     *
     * @return List of patient Fiscal Code
     */
    public static ArrayList<PatientFiscalCode> getAllPatientId(){
        ArrayList<PatientFiscalCode> patientsIds = new ArrayList<>();
        String query = "SELECT $dtId FROM DIGITALTWINS WHERE IS_OF_MODEL('"+ Constants.PATIENT_MODEL_ID + "')";
        PagedIterable<BasicDigitalTwin> pageableResponse = Client.getClient().query(query, BasicDigitalTwin.class);
        pageableResponse.forEach(r-> patientsIds.add(new PatientFiscalCode(r.getId())));
        return patientsIds;
    }
}
