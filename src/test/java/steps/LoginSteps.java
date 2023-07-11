package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Assert;
import org.w3c.dom.DOMConfiguration;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.Log;

public class LoginSteps extends CommonMethods {

    @Given("user is navigated to HRMS application")
    public void user_is_navigated_to_hrms_application() {
        //to lunch Chrome browser
        /*driver = new ChromeDriver();
        driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));*/
        openBrowserAndNavigateToURL();
    }

    @When("user enters valid admin username and password")
    public void user_enters_valid_admin_username_and_password() {
        //entering the credentials
        //WebElement usernameField = driver.findElement(By.xpath("//input[@name='txtUsername']"));
        //WebElement passwordFiled = driver.findElement(By.xpath("//input[@name='txtPassword']"));
        /** 1st approach -> with the web element*/
        //usernameField.sendKeys(ConfigReader.getPropertyValue("username"));
        //passwordFiled.sendKeys(ConfigReader.getPropertyValue("password"));

        /** 2nd approach -> using common method sendText */
        //sendText(ConfigReader.getPropertyValue("username"), usernameField);
        //sendText(ConfigReader.getPropertyValue("password"), passwordFiled);

        /** final framework approach -> using object   */
        //creating the object of the class to access all the web element from it
        // LoginPage loginPage = new LoginPage();

        DOMConfigurator.configure("log4j.xml");
        Log.startTestCase("My batch 16 test case starts here");
        sendText(ConfigReader.getPropertyValue("username"), loginPage.usernameField);
        Log.info("My username has been entered");
        sendText(ConfigReader.getPropertyValue("password"), loginPage.passwordField);
        Log.info("My password has been entered");
    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        //logIn btn
        //WebElement loginButton = driver.findElement(By.xpath("//input[contains(@id, 'btn')]"));
        // loginButton.click();

        // click(loginButton);

        // LoginPage loginPage = new LoginPage();
        click(loginPage.loginButton);

    }

    @Then("user is successfully logged in the application")
    public void user_is_successfully_logged_in_the_application() {
        System.out.println("My test case is passed for log in");
         //System.out.println(10/0);
    }


    @When("user enters ess username and password")
    public void user_enters_ess_username_and_password() {
        //WebElement usernameField = driver.findElement(By.xpath("//input[@name='txtUsername']"));
        //WebElement password = driver.findElement(By.xpath("//input[@name='txtPassword']"));
        //logged in via normal employee
        //usernameField.sendKeys("dalima123");
        //passwordField.sendKeys("Hum@nhrm123")

        // sendText(ConfigReader.getPropertyValue("username"), usernameField);
        // sendText(ConfigReader.getPropertyValue("password"), passwordField);

        // LoginPage loginPage = new LoginPage();
        sendText("dalima123", loginPage.usernameField);
        sendText("Hum@nhrm123", loginPage.passwordField);
       // System.out.println(10/0);
    }

    @When("user enters invalid admin username and password")
    public void user_enters_invalid_admin_username_and_password() {
        //WebElement usernameField = driver.findElement(By.xpath("//input[@name='txtUsername']"));
        //WebElement passwordField = driver.findElement(By.xpath("//input[@name='txtPassword']"));
        //usernameField.sendKeys("admin123");
        //passwordField.sendKeys("Hum@nhrm123");

        //sendText("admin123", usernameField);
        //sendText("Hum@nhrm123", passwordField)

        // LoginPage loginPage = new LoginPage();
        sendText("admin123", loginPage.usernameField);
        sendText("Hum#n", loginPage.passwordField);

    }

    @Then("error message is displayed")
    public void error_message_is_displayed() {
        System.out.println("Error message is displayed");
    }

    @When("user enters {string} and {string} and verifying the {string} for the combinations")
    public void user_enters_and_and_verifying_the_for_the_combinations
            (String username, String password, String errorMessageExpected) {
        sendText(username, loginPage.usernameField);
        sendText(password, loginPage.passwordField);
        click(loginPage.loginButton);
        //fetching the error message from the web element
        String errorMessageActual = loginPage.errorMessageField.getText();
        //errorMessageExpected -> error message coming from feature file so then, which we can compare
        //message:"values does not match" -> optional
        Assert.assertEquals("values does not match", errorMessageExpected, errorMessageActual);

    }
}
