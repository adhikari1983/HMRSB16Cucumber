package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //features we use-to provide the path of all the feature files or to one .feature file
        features = "src/test/resources/features",

        //glue keyword we use-to provide the path of the package where all the step defs are available
        glue = {"steps"},

        //when dryRun = true -> it stops actual execution, and then; it quickly scans all the steps def
        // to provide the missing step definition
        //when dryRun = false -> it executes the step defs
        dryRun = false,

        //tags -> runs/executes the test case of our choice/selection
        //tags = "@regression" -> at least one tag should be there
        // tags = "@regression or @smoke" -> mostly used test EXECUTIONS in work environment
        // tags = "@sprint1 or sprint2"   -> run with either one condition is true
        // tags = "@sprint1 and sprint2"  -> boolean value should be true to run means both sprint needs to be there
        tags = "@login",  /** most of the time we play with tags & some time dryRun just 2get missing step*/

        //it means, sometimes the console output for cucumber test is having some
        //irrelevant information. But, when you set it to true, it removes all that
        //irrelevant information from the console and will give you simple output
        monochrome = true,

        //it used to print all the steps in console
        // this report will be generated under target folder
        plugin = {"pretty", "html:target/cucumber.html", "json:target/cucumber.json",
                     "rerun:target/failed.txt"}
)
public class RunnerClass {
}
