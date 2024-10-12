package apis.API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING) according to alphabetical name ascending order
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExamples {
     /* baseURI = baseURL  + endPoint
     given   - preparation
     when    - hitting the endPoint
     on this context => base URI = base URL here
     then    - assertion/verification

     with this java is unable to find the url
    String baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    assigning the URL to the static variable, and then assigning that static variable to the instance variable */
    /**
      baseURI is in gray color, means it looks like it is never used but this is how it works in "Rest Assured"
      by defining it as an instance here => its becomes the Central-junction, it will direct to the url by adding below
      defined endpoint url.
      Rest Assured makes the internal link with the url just by defining here as an instance variable, this is the
      functionality of Rest Assured.
      => baseURI -> gray, but  RestAssured.baseURI -> in Not-gray color that means internally the link is active.
     */
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api"; //further, talk with CHATGPT
    // value of token should be same as postman
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2OTA5MzI0NjcsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY5MDk3NTY2NywidXNlcklkIjoiNTcyOCJ9.ft7JyvJIPBl3LYuJ50C5WWLL-PmlVKtKXxeFw_syLAs";
    static String employee_id_fromPostCall;

    //in this method we are going to create an Employee
    //we need Headers, Body to prepare the request
    @Test
    public void aCreateEmployee() {
        // 1. prepare the request
        // RequestSpecification class -> tells java class -> that it is a request load to hit the end point
        RequestSpecification request = given()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .body("{\n" +
                        "  \"emp_firstname\": \"Priya III\",\n" +
                        "  \"emp_lastname\": \"Hari\",\n" +
                        "  \"emp_middle_name\": \"Despandya\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"2023-07-28\",\n" +
                        "  \"emp_status\": \"Working\",\n" +
                        "  \"emp_job_title\": \"SDET\"\n" +
                        "}");
        // 2. hitting the endPoint -> we get the response as =>  Response response
        Response response = request.when().post("/createEmployee.php");

        // 3. verifying the response
        response.then().assertThat().statusCode(201);

        // this is how we print out result in conventional java
        //System.out.println(response);

        //but for RestAssured -> this method; we used to print the response of apis.API in console
        response.prettyPrint();

        //verify all the values and headers from response
        response.then().assertThat().body("Employee.emp_firstname", equalTo("Kripa"));
        response.then().assertThat().body("Employee.emp_middle_name", equalTo("Adhikari"));
        response.then().assertThat().body("Message", equalTo("Employee Created"));
        response.then().assertThat().header("X-Powered-By", equalTo("PHP/7.2.18"));

        // it will return the employee id and saves it in variable employeeId
        employee_id_fromPostCall = response.jsonPath().getString("Employee.employee_id");
        System.out.println(employee_id_fromPostCall);

    }

    @Test
    public void bGetCreatedEmployee() {
        RequestSpecification request = given()
                .header("Authorization", token)
                .queryParam("employee_id", employee_id_fromPostCall);

        Response response = request.when().get("/getOneEmployee.php");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();

//on the response of this get call response body employee object key named as "employee" not like Employee in post call
        String tempEmpIdFromGetCall = response.jsonPath().getString("employee.employee_id");
        Assert.assertEquals(employee_id_fromPostCall, tempEmpIdFromGetCall);

    }

    @Test
    public void cUpdateEmployee() {
        RequestSpecification request = given()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .body("{\n" +
                        "        \"employee_id\": \"" + employee_id_fromPostCall + "\",\n" +
                        "        \"emp_firstname\": \"Sajana\",\n" +
                        "        \"emp_middle_name\": \"MS\",\n" +
                        "        \"emp_lastname\": \"Basti\",\n" +
                        "        \"emp_birthday\": \"1987-07-23\",\n" +
                        "        \"emp_gender\": \"F\",\n" +
                        "        \"emp_job_title\": \"Full stack developer\",\n" +
                        "        \"emp_status\": \"working\"\n" +
                        "    }");

        Response response = request.when().put("/updateEmployee.php");
        response.then().assertThat().statusCode(200);
                                     //HamCrest Matchers example ............................
        response.then().assertThat().body("Message", equalTo("Employee record Updated"));
    }


    @Test
    public void dGetUpdatedEmployee() {
        bGetCreatedEmployee();
    }

    //Homework - Make partial employee update request and get the partially updated employee
}
