package apis.APIStepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utils.APIConstants;
import utils.APIPayloadConstants;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class APIWorkFlowSteps {
    //moved in the apis.API constant
    //public static final String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    RequestSpecification request;
    Response response;
    public static String employee_id;

    // ------------------------- POST CALL => @api Scenario: Creating the employee using apis.API ------------------------------------------
   /* @Given("a request is prepared for creating an employee")
     public void a_request_is_prepared_for_creating_an_employee() {
        request = given().header("Content-Type", "application/json")
                .header("Authorization", GenerateTokenStep.token).body("{\n" +
                        "  \"emp_firstname\": \"Monica\",\n" +
                        "  \"emp_lastname\": \"Rani\",\n" +
                        "  \"emp_middle_name\": \"Sharma\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"2023-07-28\",\n" +
                        "  \"emp_status\": \"Working\",\n" +
                        "  \"emp_job_title\": \"SDET\"\n" +
                        "}");
    }
    public void a_request_is_prepared_for_creating_an_employee() {
        request = given().header(APIConstants.HEADER_CONTENT_TYPE_KEY,
                        APIConstants.HEADER_CONTENT_TYPE_VALUE)
                .header(APIConstants.HEADER_AUTHORIZATION_KEY, GenerateTokenStep.token).body("{\n" +
                        "  \"emp_firstname\": \"Monica\",\n" +
                        "  \"emp_lastname\": \"Rani\",\n" +
                        "  \"emp_middle_name\": \"Sharma\",\n" +
                        "  \"emp_gender\": \"F\",\n" +
                        "  \"emp_birthday\": \"2023-07-28\",\n" +
                        "  \"emp_status\": \"working\",\n" +
                        "  \"emp_job_title\": \"SDET\"\n" +
                        "}");
    }*/
    // # 1. Making the POST call with normal JSON string format = hard coded
    @Given("a request is prepared for creating an employee")
    public void a_request_is_prepared_for_creating_an_employee() {
        request = given().header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE)
                .header(APIConstants.HEADER_AUTHORIZATION_KEY, GenerateTokenStep.token)
                .body(APIPayloadConstants.createEmployeePayload());
    }

    @When("a POST call is made to create an employee")
    public void a_post_call_is_made_to_create_an_employee() {
        response = request.when().post(APIConstants.CREATE_EMPLOYEE_URI);
        response.prettyPrint();
    }

    @Then("the status code for creating an employee is {int}") //this statusCode => from feature file that 201
    public void the_status_code_for_creating_an_employee_is(int statusCode) {
        //(int statusCode) => statusCode as parameter, we r passing it as argument statusCode = 201 down here
        response.then().assertThat().statusCode(statusCode);
    }

    @Then("the employee created contains key {string} and value {string}")
    public void the_employee_created_contains_key_and_value(String key, String value) {
        //using HamCrest matcher
        response.then().assertThat().body(key, equalTo(value));
    }

    @Then("the employee id {string} is stored as a global variable")
    public void the_employee_id_is_stored_as_a_global_variable(String empId) {
        employee_id = response.jsonPath().getString(empId);
        System.out.println(employee_id);
    }

    // ------------------------- POST CALL => @json Scenario: Creating the employee using apis.API ------------------------
    // # 2. Making the POST call again with normal JSON object ...............
    @Given("a request is prepared for creating an employee using json payLoad")
    public void a_request_is_prepared_for_creating_an_employee_using_json_pay_load() {
        request = given().header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE)
                .header(APIConstants.HEADER_AUTHORIZATION_KEY, GenerateTokenStep.token)
                .body(APIPayloadConstants.createEmployeeJsonPayload());
    }

    // ------------------------- POST CALL => @dynamic Scenario : Creating the employee using apis.API ------------------------------------
    //# 3. Making the POST call again passing the data directly from feature file for dynamic scenario ...............
    @Given("a request is prepared for creating an employee with data {string}, {string},{string},{string}, {string}, {string}, {string};")
    public void a_request_is_prepared_for_creating_an_employee_with_data
    (String firstName, String lastName, String middleName, String gender,
     String dob, String status, String jobTitle) {
        request = given().header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE)
                .header(APIConstants.HEADER_AUTHORIZATION_KEY, GenerateTokenStep.token)
                .body(APIPayloadConstants.createEmployeeJsonPayloadDynamic(
                        firstName, lastName, middleName, gender, dob, status, jobTitle));
    }

    // ***********************************************************************************************************************
    // ------------------------- GET CALL => @api Scenario: Get the created employee------------------------------------------
    @Given("a request is prepared for retrieving an employee")
    public void a_request_is_prepared_for_retrieving_an_employee() {
        request = given().header(APIConstants.HEADER_AUTHORIZATION_KEY, GenerateTokenStep.token)
                .queryParam("employee_id", employee_id);
    }

    @When("a GET call is made to retrieve the employee")
    public void a_get_call_is_made_to_retrieve_the_employee() {
        response = request.when().get(APIConstants.GET_ONE_EMPLOYEE_URI);
        response.prettyPrint();
    }


    @Then("the status code for this employee is {int}")
    public void the_status_code_for_this_employee_is(Integer statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }

    // verifying one element(one key's value) of the response payload with request payload
    @Then("the employee id {string} must match as globally stored employee id")
    public void the_employee_id_must_match_as_globally_stored_employee_id(String empIDFromDB) {
        // empIDFromDB -> from GET call
        String actual_empID = response.jsonPath().getString(empIDFromDB);
        // employee_id -> from POST call
        Assert.assertEquals(employee_id, actual_empID);


    }

    // verifying the elements of the response payload with request payload comparing one by one
    @Then("this employee data at {string} object matches with the data used to create the employee")
    public void this_employee_data_at_object_matches_with_the_data_used_to_create_the_employee
    (String employeeObject, io.cucumber.datatable.DataTable dataTable) {
        //from feature file => "employee" -> employeeObject
        /** the below data
         | emp_firstname | emp_lastname | emp_middle_name | emp_gender | emp_birthday | emp_status | emp_job_title |
         | Ganesh        | Basti        | MS              | Male       | 1987-07-23   | working    | SDET          |
         ..............for now its 1 row but there can be many rows of data to test..............................
         1st it is in the form of employee data at "employee" object in datatable of cucumber framework, then
         we re-structured and stored in the list of map as => List<Map<String, String>> exceptedData to
         manipulate further.  */
        List<Map<String, String>> exceptedData = dataTable.asMaps();
        //from GET call
        //since we need whole object, we are calling .get method instead of .getString method
        Map<String, String> actualData = response.body().jsonPath().get(employeeObject);
        /**
         {
         "Message": "Employee Created",                                    parameter     = argument
         "Employee": { -------------------------------------------->.get(employeeObject) = Employee
         "employee_id": "94677A",
         "emp_firstname": "Ganesh",
         "emp_middle_name": "MS",
         "emp_lastname": "Basti",
         "emp_birthday": "1987-07-23",
         "emp_job_title": "SDET",
         "emp_status": "Working"
         }
         }
         */

        for (Map<String, String> map : exceptedData) {
            // to keep the insertion order & avoid duplicates
            // all the keys we got from feature file employeeObject
            Set<String> keys = map.keySet();
            for (String key : keys) {
                //from the key , we will get value from exceptedData(dataTable==from featureFile)
                String expectedValue = map.get(key);
                //from the key , we will get value from actualData from response.body().jsonPath().get(employeeObject)
                String actualValue = actualData.get(key);
                Assert.assertEquals(expectedValue, actualValue);
            }
        }
    }

    // ***********************************************************************************************************************
    // ------------------------- PATCH CALL => @partialUpdate Scenario: Get the created employee------------------------------------------
    @Given("the request is prepared to update the employee firstname to {string}")
    public void the_request_is_prepared_to_update_the_employee_firstname_to(String name) {
        request = given().header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE)
                .header(APIConstants.HEADER_AUTHORIZATION_KEY, GenerateTokenStep.token)
                .body(APIPayloadConstants.updatePartialPayload(employee_id, "emp_firstname",name));
                /*.body("{\n" +
                        "  \"employee_id\": \""+employee_id+"\",\n" +
                        "  \"emp_firstname\": \""+name+"\"}"); */
    }

    @When("a PATCH call is made to update the employee")
    public void a_patch_call_is_made_to_update_the_employee() {
        // Write code here that turns the phrase above into concrete actions
        response = request.patch(APIConstants.UPDATE_PARTIALLY_EMPLOYEE_URI);
    }

    @Then("the status code for partially updating an employee is {int}")
    public void the_status_code_for_partially_updating_an_employee_is(Integer code) {
        // Write code here that turns the phrase above into concrete actions
        response.then().assertThat().statusCode(code);
        response.prettyPrint();
    }

    @Then("the employee updated has the updated firstname {string}")
    public void the_employee_updated_has_the_updated_firstname(String string) {
          // to do...........................
    }
}
