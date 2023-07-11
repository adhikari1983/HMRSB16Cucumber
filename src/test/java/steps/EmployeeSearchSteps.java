package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.DashboardPage;
import utils.CommonMethods;

import java.time.Duration;

public class EmployeeSearchSteps extends CommonMethods {

    /**
     * step def order doesn't matter, just link the step defs
     */
    @When("user clicks on PIM option and employee list option")
    public void user_clicks_on_pim_and_employee_list_option() {
        //we are clicking on pim and emp option
        //WebElement pimOption = driver.findElement(By.xpath("//a[@id='menu_pim_viewPimModule']"));
        //pimOption.click();
        //click(pimOption);

        click(dashboardPage.pimOption);
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // WebElement empListOption = driver.findElement(By.xpath("//a[@id='menu_pim_viewEmployeeList']"));
        //empListOption.click();
        //click(empListOption);

        click(dashboardPage.empListOption);
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @When("user enters valid employee id")
    public void user_enters_valid_employee_id() {
        //WebElement searchIdTextBox = driver.findElement(By.xpath("//input[@id='empsearch_id']"));
        //searchIdTextBox.sendKeys("54469A");
        sendText("54469A", employeeSearchPage.idTextField);
    }

    @When("user clicks on search button")
    public void user_clicks_on_search_button() {
        //WebElement searchButton = driver.findElement(By.xpath("//input[@id='searchBtn']"));
        //searchButton.click();
        //click(searchBtn);c
        click(employeeSearchPage.searchButton);
    }

    @Then("user is able to see employee information")
    public void user_is_able_to_see_employee_information() {
        System.out.println("Employee information is displayed");
    }

    @When("user enters valid employee name in name text box")
    public void user_enters_valid_employee_name_in_name_text_box() {
        // WebElement empNameField = driver.findElement(By.id("empsearch_employee_name_empName"));
        //empNameField.sendKeys("Selab");
        sendText("Selab", employeeSearchPage.nameTextField);
    }

}
