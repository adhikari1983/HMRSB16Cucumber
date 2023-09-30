package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

//only for local execution purpose
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "@target/failed.txt",
        plugin = {"pretty"}
)

public class FailedRunner {

}
