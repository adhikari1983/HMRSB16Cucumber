package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.CommonMethods;

public class Hooks extends CommonMethods {

    @Before
    public  void start(){
        openBrowserAndNavigateToURL();
    }


    /*
    Scenario scenario -> holds the complete info of the test execution e.g. like Scenario id/name/steps
    e.g. Scenario scenario -> can be  @sprint1 @employee @login
                                       Scenario: valid ess login
        so here scenario ->  valid ess login
        ****** Also, responsible to attach the image of report with it to the report ******
    */
    @After
    public void end(Scenario scenario){
        //we need this variable because takeScreenshot() method returns -> array of byte
        byte[] pic;
        //here we are going to capture the screenshot and attaching it to the report
        if(scenario.isFailed()) {
            pic = takeScreenshot(driver,"failed/" + scenario.getName());
        }else {
            pic = takeScreenshot(driver,"passed/" + scenario.getName());
        }
        // attaching this screenshot in the report
        scenario.attach(pic,"image/png",scenario.getName());
        closeBrowser();
    }
}
