package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.checkerframework.checker.units.qual.K;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;
import utils.Constants;
import utils.ExcelReader;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethods {
    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
        // WebElement pimOption = driver.findElement(By.id("menu_pim_viewPimModule"));
        //pimOption.click();

        // click(pimOption);

        click(dashboardPage.pimOption);

    }

    @When("user clicks on add employee button")
    public void user_clicks_on_add_employee_button() {
        // WebElement addEmployeeButton = driver.findElement(By.id("menu_pim_addEmployee"));
        // addEmployeeButton.click();

        // click(addEmployeeButton);

        click(dashboardPage.addEmployeeButton);
    }

    @When("user enters firstname and lastname")
    public void user_enters_firstname_and_lastname() {
        // WebElement firstNameTextField = driver.findElement(By.id("firstName"));
        // firstNameTextField.sendKeys("aendro");

        // sendText("aendro", firstNameTextField);

        sendText("aendro", addEmployeePage.firstNameField);

        // WebElement lastNameTextField = driver.findElement(By.id("lastName"));
        //lastNameTextField.sendKeys("farewell");

        // sendText("lastname", lastNameTextField);

        sendText("farewell", addEmployeePage.lastNameField);
    }

    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
        //WebElement saveButton = driver.findElement(By.id("btnSave"));
        //saveButton.click();

        // click(saveButton);

        click(addEmployeePage.saveButton);
    }

    @Then("employee added successfully")
    public void employee_added_successfully() {
        System.out.println("Employee added successfully");
    }

    @When("user enters {string} and {string} and {string}")
    public void user_enters_and_and(String firstName, String middleName, String lastName) {
        sendText(firstName, addEmployeePage.firstNameField);
        sendText(middleName, addEmployeePage.middleNameField);
        sendText(lastName, addEmployeePage.lastNameField);
    }

    @When("user enters {string} and {string} and {string} in data driven format")
    public void user_enters_and_and_in_data_driven_format(String firstName, String middleName, String lastName) {
        sendText(firstName, addEmployeePage.firstNameField);
        sendText(middleName, addEmployeePage.middleNameField);
        sendText(lastName, addEmployeePage.lastNameField);
    }

    @When("user enters firstname and middlename and lastname and verify employee has added")
    public void user_enters_firstname_and_middlename_and_lastname_and_verify_employee_has_added
            (io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        // For other transformations you can register a DataTableType.
        /** we need list of maps to get multiple values from datable which is coming from feature file*/
        // dataTable.asMaps(); -> is returning that's why it's ->  List<Map<String,String>> employeeNames
        List<Map<String,String>> employeeNames = dataTable.asMaps();

        /** So, cucumber is creationg the table as dataTable for us , from the vaues we
          provided in feature file as below
                                     | firstName | middleName | lastName |
                                     | Hari      | ms         | Poudel   |
                                     | Shyam     | ms         | Adhikari |
                                     | Gopal     | ms         | Adhikari |
         */

        //in one iteration gives  -> one Map of employee
        for(Map<String,String> employee : employeeNames){
            //getting the value against the key in map
            String firstNameValue = employee.get("firstName");
            String middleNameValue = employee.get("middleName");
            String lastNameValue = employee.get("lastName");

            //filling the name in the fields
            sendText(firstNameValue,addEmployeePage.firstNameField);
            sendText(middleNameValue, addEmployeePage.middleNameField);
            sendText(lastNameValue, addEmployeePage.lastNameField);
            click(addEmployeePage.saveButton);
            //after adding one employee we will add another employee
            //for this we are clicking add employee btn in the loop it-self
            click(dashboardPage.addEmployeeButton);
        }
    }

    @When("user adds multiple employees using excel from {string} and verify it")
    public void user_adds_multiple_employees_using_excel_from_and_verify_it
            (String sheetName) throws InterruptedException {
        // here we are getting the data from excel file using parameters
        List<Map<String, String>> newEmployees = ExcelReader.read(sheetName, Constants.EXCEL_READER_PATH);

        Iterator<Map<String, String>> iterator = newEmployees.iterator();
        //it will check whether we have new element/value or not
        while(iterator.hasNext()){
            //in this map, we have data for every single employee one by one it will give us  that data/new employee
            Map<String, String> mapNewEmp = iterator.next();
            //we are filling the employee data now using mapNewEmp variable
            //BATCH 16,
            sendText(mapNewEmp.get("firstName"),addEmployeePage.firstNameField);
            sendText(mapNewEmp.get("lastName"),addEmployeePage.lastNameField);
            sendText(mapNewEmp.get("middleName"), addEmployeePage.middleNameField);
            sendText(mapNewEmp.get("photograph "), addEmployeePage.photograph);

            //
            if(!addEmployeePage.checkBoxLocator.isSelected()){
                click(addEmployeePage.checkBoxLocator);
            }
            sendText(mapNewEmp.get("username"), addEmployeePage.usernameTextFieldBox);
            sendText(mapNewEmp.get("password"), addEmployeePage.passwordTextFieldBox);
            sendText(mapNewEmp.get("confirmPassword"), addEmployeePage.confirmPasswordBox);

            //here we are fetching the employee id from the UI using the get attribute method
            String empIdValue = addEmployeePage.employeeIdField.getAttribute("value");

            //just an example showing that we can Assert anywhere
            Assert.assertTrue(addEmployeePage.saveButton.isDisplayed());

            Thread.sleep(2500);
            click(addEmployeePage.saveButton);
            // we have to verify that the employee has been added
            click(dashboardPage.empListOption);
            //searching the employee using id which we have got
            sendText(empIdValue, employeeSearchPage.idTextField);

            click(employeeSearchPage.searchButton);

            //print the value from the table row
            List<WebElement> rowData = driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr"));
            for (int i = 0; i < rowData.size(); i++) {
                System.out.println("I am inside the loop");
                //it will return one by one all the data from the row
                String rowText = rowData.get(i).getText();
                //it will print the complete row data
                //o/p of this will be empid space firstname space middlename space lastname
                System.out.println(rowText);
                //we have to verify this data against the data coming from excel
                String expectedData = empIdValue + " " + mapNewEmp.get("firstName") +
                                      " " + mapNewEmp.get("middleName") + " " + mapNewEmp.get("lastName");

                Assert.assertEquals(expectedData, rowText);
                //we can also use blow code to verify the data
                //Assert.assertEquals(expectedData.equals(rowText));
            }
            //to add more employees we need to click on add employee button
            click(dashboardPage.addEmployeeButton);

        }

    }

}
