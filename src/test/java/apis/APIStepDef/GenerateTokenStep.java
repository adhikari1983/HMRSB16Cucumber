package apis.APIStepDef;

import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.APIConstants;

import static io.restassured.RestAssured.given;

public class GenerateTokenStep {


    public static String token;

    @Given("a JWT is generated")
    public void a_jwt_is_generated() {
        // here we write the code to generate the JWT token
        RequestSpecification request = given().
                header("Content-Type", "application/json").
                body("{\n" +
                        "  \"email\": \"gorkhali@gmail.com\",\n" +
                        "  \"password\": \"Pass@123\"\n" +
                        "}");
        Response response = request.when().post(APIConstants.GENERATE_TOKEN_URI);
        response.prettyPrint();

        token = "Bearer " + response.jsonPath().getString("token");
        System.out.println(token);
    }

}
