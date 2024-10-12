package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class LoginPage extends CommonMethods {

    // this is object repository of POM
    @FindBy(id="txtUsername")
    public WebElement usernameField;

    @FindBy(id="txtPassword")
    public WebElement passwordField;

    @FindBy(id="btnLogin")
    public WebElement loginButton;

    @FindBy(xpath = "//span[@id='spanMessage']")
    public WebElement errorMessageField;



    //to initialize the all the elements of the page we have to call them inside constructor
    public  LoginPage(){
        // PageFactory.initElements -> driver gets the elements of this page,  initialize them all
        PageFactory.initElements(driver, this);
        //this driver will be called for the particular page:this
    }

}
