package WeatherPick.weatherpick.common;

public interface ResponseCode {
    //HTTP Status 200
    String SUCCESS = "SU";

    //HTTP Status 400
    String VALIDATION_FAILED = "VF";
    String DUPLICATE_EMAIL = "DE";
    String DUPLICATE_ID = "DI";
    String NOT_EXIDSTED_USER= "NU";
    String NOT_EXIDSTED_REVIEW = "NR";

    //HTTP Status 401
    String SIGN_IN_FAIL= "SF";
    String AUTHORIZATION_FAIL = "AF";

    //HTTP Status 402
    String NO_PERMISSION = "NP";

    //HTTP Status 500
    String DATABASE_ERROR = "DBE";
    String API_ERROR  = "AE";

    String NOT_EXIST_IMAGES = "NI";
}
