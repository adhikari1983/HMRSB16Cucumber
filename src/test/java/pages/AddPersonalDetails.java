package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

import java.util.List;

public class AddPersonalDetails extends CommonMethods {
    @FindBy(id = "personal_txtEmpFirstName")
    public WebElement firstNameField;

    @FindBy(id = "personal_txtEmpMiddleName")
    public WebElement middleNameField;

    @FindBy(id = "personal_txtEmpLastName")
    public WebElement lastNameField;

    ////label[normalize-space()='Gender']
    @FindBy(xpath = "//label[text()='Gender']")
    public List<WebElement> genderSelection;

    @FindBy(xpath = "//label[text()='Nationality']")
    public WebElement nationality;

    @FindBy(xpath = "//label[text()='Marital Status']")
    public WebElement maritalStatus;

    @FindBy(id = "btnSave")
    public WebElement saveButton;

    @FindBy(xpath = "//select[@id='personal_cmbMarital']")
    public WebElement maritalStatusDD;

    @FindBy(xpath="//select[@id='personal_cmbNation']")
    public WebElement nationalityDD;

    public AddPersonalDetails() {
        PageFactory.initElements(driver, this);
    }
}
