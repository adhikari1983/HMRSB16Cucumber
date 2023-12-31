package utils;

import io.restassured.RestAssured;

public class APIConstants {
    //yup baseURI is a Central-connection junction, Rest Assured library will make the internal connection with the url
    //just by defining here and all the endpoints are appended on this baseURI
                          //1. define here
    public static final String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
                                             //2. all the endpoints are appended on this baseURI
                                             //3. baseURI part is done
    public static final String CREATE_EMPLOYEE_URI  = baseURI+"/createEmployee.php";
    public static final String GET_ONE_EMPLOYEE_URI = baseURI+"/getOneEmployee.php";
    public static final String UPDATE_EMPLOYEE_URI  = baseURI+"/updateEmployee.php";
    public static final String UPDATE_PARTIALLY_EMPLOYEE_URI  = baseURI+"/updatePartialEmplyeesDetails.php";
    public static final String GENERATE_TOKEN_URI   = baseURI+"/generateToken.php";
    //similarly you can add endpoint for remainings


    // we need to add headers as well
    public static final String HEADER_CONTENT_TYPE_KEY = "Content-Type";
    public static final String HEADER_CONTENT_TYPE_VALUE = "application/json";
    public static final String HEADER_AUTHORIZATION_KEY = "Authorization";
}
