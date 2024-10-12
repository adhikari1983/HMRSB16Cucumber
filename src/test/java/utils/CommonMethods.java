package utils;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.core.config.Configurator;
//import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.PageInitializer;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class CommonMethods extends PageInitializer {

    public static WebDriver driver;


    public static void openBrowserAndNavigateToURL() {
        /* reading the config.properties file
          read the property file from the given path                -> & Put it in ConfigReader
        the path is coming from the constants */
        ConfigReader.readProperties(Constants.CONFIG_READER_PATH);
        /* ConfigReader => hold the Properties
           Properties properties = ConfigReader.readProperties(Constants.CONFIG_READER_PATH);

          extracting the browser as a key from that config file    -> & fetching from the ConfigReader
          read the value of the property "url" from the config file  */
        switch (ConfigReader.getPropertyValue("browser")) {// switch (browser){  initially was like this, but we made it generic
            case "chrome":
// these next 4/5 lines added to execute the test scripts on the Jenkins server w/o UI w/remote origin
                ChromeOptions chromeOptions = new ChromeOptions();
               // chromeOptions.setHeadless(true); -> deprecated after selenium 4.11.0
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--remote-allow--origins=*");
                chromeOptions.addArguments("--headless=new");

                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                driver = new FirefoxDriver();
                break;
        }

        driver.manage().window().maximize();
        driver.get(ConfigReader.getPropertyValue("url"));// driver.get(URL) initially was like this, but we made it generic
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        // this method is going to initialize all the objects
        initializePageObjects();// This will initialize all the pages we have in our Page
        //PageInitializer class along with the launching of application
        // To configure the File and pattern it has
       // DOMConfigurator.configure("log4j2.xml");
        Configurator.initialize(null, "log4j2.xml"); // Configure Log4j 2.x
        Log.startTestCase("This is the beginning of my Test case");
        Log.info("My test case is executing right now");
        Log.warning("My test case might have some trivial issues");

    }

    //close the browser
    public static void closeBrowser() {
        if (driver != null) {
            Log.info("This test case is about to get completed");
            Log.endTestCase("This test case is finished");
            driver.quit();
        }
    }

    public static void sendText(String text, WebElement element) {
        element.clear();
        element.sendKeys(text);
    }

    //..................................................................................
    public static WebDriverWait getWait() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        return wait;
    }

    public static void waitForClickability(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void click(WebElement element) {
        waitForClickability(element);
        element.click();
    }

    //.....................................................................................
    public static void selectFromDropdown(WebElement dropDown, String visibleText) {
        Select sel = new Select(dropDown);
        sel.selectByVisibleText(visibleText);
    }

    public static void selectFromDropdown(String value, WebElement dropDown) {
        Select sel = new Select(dropDown);
        sel.selectByValue(value);
    }

    public static void selectFromDropdown(WebElement dropDown, int index) {
        Select sel = new Select(dropDown);
        sel.selectByIndex(index);
    }
    /** type 1 radio button DOM structure */
//    public static void radioBtnSelection(WebElement radioBtnOptions){
//         if(radioBtnOptions.getAttribute("type").equals("radio")){
//            click(radioBtnOptions);
//         }
//    }

    /** type 2 radio button DOM structure */
//    public static void radioBtnSelection(List<WebElement> radioBtnOptions, String value){
//        for(WebElement radioButton: radioBtnOptions){
//            if(radioButton.getAttribute("value").equalsIgnoreCase(value)){
//                click(radioButton);
//                break;
//            }
//        }
//    }

    /**
     * type 3 radio button DOM structure
     * While ./following-sibling::label selects the label element that is a following sibling of the current element,
     * ./preceding-sibling::label selects the label element that is a preceding sibling of the current element.
     */
    public static void selectRadioButtonByValue(String value, List<WebElement> radioButtons) {
        for (WebElement radioButton : radioButtons) {
            WebElement labelElement = radioButton.findElement(By.xpath("./following-sibling::label"));
            if (labelElement.getText().equalsIgnoreCase(value)) {
                click(radioButton);
                break;
            }
        }
    }


    // to take screen-shots and store
    // public static void takeScreenshot(String fileName){ // Return type is changed to byte of arrays so then we can
    public static byte[] takeScreenshot(String fileName) {  // attached 2report & send it.
        TakesScreenshot ts = (TakesScreenshot) driver;
        // to create the image
        //we write this line because cucumber accepts array of byte for screenshot
        byte[] picBytes = ts.getScreenshotAs(OutputType.BYTES);  //=> then changed into bytes format
        //to generate and store the files
        File screenShot = ts.getScreenshotAs(OutputType.FILE);   //=> first get the image in the file format than ^
        //in case if it doesn't find file name or path it will throw an exception
        try {
            FileUtils.copyFile(screenShot,
                    new File(Constants.SCREENSHOT_FILEPATH + fileName + " "
                            + getTimeStamp("yyyy-MM-dd-HH-mm-ss") + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return picBytes;
    }

    public static String getTimeStamp(String pattern) {
        //it returns the current date and time in java
        Date date = new Date();
        //this function sdf used to format the date as per the pattern we are passing
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        //this line is going to return the formatted date
        return sdf.format(date);
    }


}
