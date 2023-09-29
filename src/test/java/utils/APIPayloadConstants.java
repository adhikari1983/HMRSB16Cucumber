package utils;

import org.json.JSONObject;

public class APIPayloadConstants {

    public static String createEmployeePayload() {
    // this payload body in the String format == is the same way that we passed in the request payload with Postman in string format
    // basically it's a hard coded data in the form of String
        String createEmployeePayLoad = "{\n" +
                "  \"emp_firstname\": \"Ganesh\",\n" +
                "  \"emp_lastname\": \"Basti\",\n" +
                "  \"emp_middle_name\": \"MS\",\n" +
                "  \"emp_gender\": \"M\",\n" +
                "  \"emp_birthday\": \"1987-07-23\",\n" +
                "  \"emp_status\": \"Working\",\n" +
                "  \"emp_job_title\": \"SDET\"\n" +
                "}";

        return createEmployeePayLoad;
    }

    public static String createEmployeeJsonPayload() {
        // stored in the form of jason and converted back to the string later
        //ist converting into json object
        JSONObject object = new JSONObject();
        object.put("emp_firstname", "Kartik");
        object.put("emp_lastname", "SonOfShiva");
        object.put("emp_middle_name", "Jestha");
        object.put("emp_gender", "M");
        object.put("emp_birthday", "1987-07-23");
        object.put("emp_status", "Working");
        object.put("emp_job_title", "SDET");
        //converting json object back to string
        return object.toString();
    }

    /**
     with this method we can pass the parameter from the feature file, or we
     can also use the EXCEL SHEET to get the data
     */
    public static String createEmployeeJsonPayloadDynamic
            (String firstName, String lastName, String middleName,
             String gender, String dob, String jobStatus, String jobTitle) {
        JSONObject object = new JSONObject();
        object.put("emp_firstname", firstName);
        object.put("emp_lastname", lastName);
        object.put("emp_middle_name", middleName);
        object.put("emp_gender", gender);
        object.put("emp_birthday", dob);
        object.put("emp_status", jobStatus);
        object.put("emp_job_title", jobTitle);

        return object.toString();
    }
    public static String updatePartialPayload(String empID, String key, String value) {
        JSONObject object = new JSONObject();
        object.put("employee_id", empID);
        object.put(key, value);
        return object.toString();
    }
}
