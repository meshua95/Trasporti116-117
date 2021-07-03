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
import utils.WaitForClientResponse;
import utils.errorCode.QueryTimeOutException;
import java.util.ArrayList;

/**
 * Get patient digital twin API
 */
public class GetPatient {
    private GetPatient(){}

    /**
     * Get the patient for specific booking
     *
     * @param  bookingId booking of which you want the patient
     * @return Fiscal Code of the patient
     * @throws QueryTimeOutException if the server takes too long to respond
     */
    public static PatientFiscalCode getPatientIdByBookingId(BookingTransportId bookingId) throws QueryTimeOutException {
        String query = "SELECT target.$dtId " +
                "FROM DIGITALTWINS source " +
                "JOIN target RELATED source.transport " +
                "WHERE source.$dtId = '"+ bookingId.getId() +"'";

        PagedIterable<JSONObject> pageableResponse = Client.getClient().query(query, JSONObject.class);
        WaitForClientResponse.waitForClientResponse(pageableResponse);

        return new PatientFiscalCode(pageableResponse.stream().findFirst().get().get("$dtId").toString());
    }

    /**
     * Get all patients
     *
     * @return List of patient Fiscal Code
     * @throws QueryTimeOutException if the server takes too long to respond
     */
    public static ArrayList<PatientFiscalCode> getAllPatientId() throws QueryTimeOutException {
        ArrayList<PatientFiscalCode> patientsIds = new ArrayList<>();
        String query = "SELECT $dtId FROM DIGITALTWINS WHERE IS_OF_MODEL('"+ Constants.PATIENT_MODEL_ID + "')";
        PagedIterable<BasicDigitalTwin> pageableResponse = Client.getClient().query(query, BasicDigitalTwin.class);

     //   WaitForClientResponse.waitForClientResponseIfExist(pageableResponse);

        pageableResponse.forEach(r-> patientsIds.add(new PatientFiscalCode(r.getId())));
        return patientsIds;
    }
}
