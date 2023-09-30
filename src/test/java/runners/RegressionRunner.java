package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

//only for regression execution purpose
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"steps"},
        dryRun = false,
        tags = "@regression",  /** most of the time we play with tags & some time dryRun just 2get missing step*/
        monochrome = true,
        plugin = {"pretty", "html:target/cucumber.html", "json:target/cucumber.json"}
)
public class RegressionRunner {
}
