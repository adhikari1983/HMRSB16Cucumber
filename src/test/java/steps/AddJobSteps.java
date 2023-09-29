package steps;

import io.cucumber.java.en.Then;
import org.junit.Assert;
import utils.CommonMethods;
import utils.DBUtils;

import java.util.List;
import java.util.Map;

public class AddJobSteps extends CommonMethods {

    String jTitleFrontEnd;
    String jDescFrontEnd;
    String jNoteFrontEnd;

    @Then("user clicks on the admin button")
    public void user_clicks_on_the_admin_button() {
        click(dashboardPage.adminButton);
    }

    @Then("user click on the job")
    public void user_click_on_the_job() {
        click(dashboardPage.adminJobButton);
    }

    @Then("user Click on Job Title")
    public void user_click_on_job_title() {
        click(dashboardPage.adminJobTitleButton);
    }

    @Then("user clicks on the add button")
    public void user_clicks_on_the_add_button() {
        click(jobPage.addButton);
    }

    @Then("user enters job {string} title")
    public void user_enters_job_title(String title) {
        sendText(title, jobPage.jobTitleF);
        jTitleFrontEnd = title;
    }

    @Then("user enters job description {string}")
    public void user_enters_job_description(String jobDescription) {
        sendText(jobDescription, jobPage.jobDescF);
        jDescFrontEnd = jobDescription;
    }

    @Then("user enters job note {string}")
    public void user_enters_job_note(String note) {
        sendText(note, jobPage.jobNoteF);
        jNoteFrontEnd = note;
    }

    @Then("user clicks on the save button here")
    public void user_clicks_on_the_save_button_here() {
        click(jobPage.jobSvBtn);
    }

    @Then("verify data is stored properly in database")
    public void verify_data_is_stored_properly_in_database() {
        String query = "select * from ohrm_job_title where job_title='"
                + jTitleFrontEnd + "' and job_description='" + jDescFrontEnd + "' and note='" + jNoteFrontEnd + "';";
        List<Map<String, String>> data = DBUtils.fetch(query);

        Map<String, String> firstRow = data.get(0);
        String jTitleBE=firstRow.get("job_title");
        String jDescBE=firstRow.get("job_description");
        String jNoteBE=firstRow.get("note");

        Assert.assertEquals(jTitleFrontEnd,jTitleBE);
        Assert.assertEquals(jDescFrontEnd,jDescBE);
        Assert.assertEquals(jNoteFrontEnd,jNoteBE);

    }

}