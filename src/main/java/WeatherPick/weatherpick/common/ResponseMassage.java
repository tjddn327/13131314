package WeatherPick.weatherpick.common;

public interface ResponseMassage {

    //HTTP Status 200
    String SUCCESS = "Success";

    //HTTP Status 400
    String VALIDATION_FAILED = "Validation failed.";
    String DUPLICATE_EMAIL = "Duplicate email.";
    String DUPLICATE_ID = "Duplicate id.";
    String NOT_EXIDSTED_USER= "This user does not exist.";
    String NOT_EXIDSTED_REVIEW  = "This review does not exist.";

    //HTTP Status 401
    String SIGN_IN_FAIL= "Login information mismatch.";
    String AUTHORIZATION_FAIL = "Authorization Failed.";

    //HTTP Status 402
    String NO_PERMISSION = "Do not have permission.";

    //HTTP Status 500
    String DATABASE_ERROR = "Database error.";
    String API_ERROR  = "Api error";

    String NOT_EXIST_IMAGES = "이미지가 존재하지 않습니다.";

}
