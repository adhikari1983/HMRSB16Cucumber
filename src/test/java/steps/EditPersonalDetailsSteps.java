package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.AddPersonalDetails;
import utils.CommonMethods;

import java.util.List;
import java.util.Map;

public class EditPersonalDetailsSteps extends CommonMethods {

    @Then("user clicks on Edit button")
    public void user_clicks_on_edit_button() {
        click(AddPersonalDetails.addPersonalDetails.saveButton);
    }

    @When("user edit firstName and middleName and lastName and gender and maritalStatus and nationality and verify it")
    public void user_edit_first_name_and_middle_name_and_last_name_and_gender_and_marital_status_and_nationality_and_verify_it
            (io.cucumber.datatable.DataTable dataTable) {
        //| firstName | middleName | lastName | gender | maritalStatus | nationality |
        List<Map<String, String>> employeeDetails = dataTable.asMaps();

        for (Map<String, String> editEmployee : employeeDetails) {
            //getting the value against the key in map
            String firstNameValue = editEmployee.get("firstName");
            String middleNameValue = editEmployee.get("middleName");
            String lastNameValue = editEmployee.get("lastName");
            String genderValue = editEmployee.get("gender");
            String maritalStatusValue = editEmployee.get("maritalStatus");
            String nationalityValue = editEmployee.get("nationality");

            //filling the name in the fields
            sendText(firstNameValue, addPersonalDetails.firstNameField);
            sendText(middleNameValue, addPersonalDetails.middleNameField);
            sendText(lastNameValue, addPersonalDetails.lastNameField);
            selectRadioButtonByValue(genderValue, addPersonalDetails.genderSelection);
            selectFromDropdown(addPersonalDetails.maritalStatusDD, maritalStatusValue);
            selectFromDropdown(addPersonalDetails.nationalityDD, nationalityValue);
        }
    }

    @When("user clicks on the save button")
    public void user_clicks_on_the_save_button() throws InterruptedException {
        click(addPersonalDetails.saveButton);

        takeScreenshot("editEmployee");

//        if (addPersonalDetails.saveButton.isDisplayed()) {
//            Assert.assertTrue(true);
//        } else {
//            Assert.assertFalse(false);
//        }

        WebElement savedMessage = driver.findElement(By.xpath("//div[@class='message success fadable']"));
        boolean isDisplayed = savedMessage.isDisplayed();
        Assert.assertTrue("Successfully Saved", isDisplayed);
    }

}
