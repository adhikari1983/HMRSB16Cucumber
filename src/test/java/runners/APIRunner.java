package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"APIStepDef"},
        dryRun = true,
        tags = "@t",
        monochrome = true,
        plugin = {"pretty"}
                )
public class APIRunner {
}
