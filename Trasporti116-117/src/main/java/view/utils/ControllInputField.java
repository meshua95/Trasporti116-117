package view.utils;

import javafx.scene.control.TextField;

import java.util.regex.Pattern;

public class ControllInputField {
    public static final String AMBULANCE_CONFIRM = "Inserita ambulanza con id: ";
    public static final String PATIENT_CONFIRM = "Inserito paziente con id: ";
    public static final String OPERATOR_CONFIRM = "Inserito operatore con id: ";
    public static final String INFO_REQUEST_CONFIRM = "Inserit richiesta di informazioni con id: ";
    public static final String SERVICE_REQUEST_CONFIRM = "Inserito richiesta di servizio con id: ";
    public static final String BOOKING_CONFIRM = "Inserita prenotazione con id: ";
    public static final String TEXT_FIELD_ERROR = "Errore nell'inserimento dei campi";
    public static final String BOOKING_DELETED = "Prenotazione cancellata correttamente";
    public static final String AMBULANCE_DELETED = "L'ambulanza è stata dismessa correttamente";

    public static Pattern HOUR_PATTERN = Pattern.compile("([01]?[0-9]|2[0-3]):[0-5][0-9]");

    public static Pattern STRING_PATTERN = Pattern.compile("[a-zA-Z]*");
    public static Pattern CITY_PATTERN = Pattern.compile("[a-zA-Z\\s]*");
    public static Pattern ADDRESS_PATTERN = Pattern.compile("[a-zA-Z0-9\\s]*");
    public static Pattern DISTRICT_PATTERN = Pattern.compile("[a-zA-Z][a-zA-Z]");
    public static Pattern NUMBER_PATTERN = Pattern.compile("[0-9]*");
    public static Pattern FISCAL_CODE = Pattern.compile("^([A-Za-z]{6}[0-9]{2}[A-Za-z]{1}[0-9]{2}[A-Za-z]{1}[0-9]{3}[[A-Za-z]]{1})$|([0-9]{11})$");

    public static Pattern POSTALCODE_NUMBER_PATTERN = Pattern.compile("[0-9]{5}");

    public static void validate(Pattern pattern, TextField input){
            if(!pattern.matcher(input.getText()).matches()){
                input.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
            }else{
                input.setStyle("-fx-text-box-border: green; -fx-focus-color: green;");
            }

    }
}
