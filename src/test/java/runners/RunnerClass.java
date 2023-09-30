package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //features we use-to provide the path of all the feature files (or to one .feature file-> only 4r learning)
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
        // tags = "@sprint1 or sprint2 or @sprint3 or @sprint4 or @sprint5"   -> run with either one condition is true
        // tags = "@sprint1 and sprint2"  -> boolean value should be true to run means both sprint needs to be there
        // tags = "@sprint1 and sprint2 and @sprint3 and @sprint4 and @sprint5"  -> boolean value should be true to run means all sprint needs to be there
        tags = "@validLogin",  /** most of the time we play with tags & some time dryRun just 2get missing step*/

        //it means, sometimes the console output for cucumber test is having some
        //irrelevant information. But, when you set it to true, it removes all that
        //irrelevant information from the console and will give you simple output
        monochrome = true,

        //it used to print all the steps in console
        //
        plugin = {"pretty",                    //it used to print all the steps in console
                  "html:target/cucumber.html", //  this html plugin is generating the report, under target folder
                  "json:target/cucumber.json", // for JSON report
                   "rerun:target/failed.txt"}
)
public class RunnerClass {
}
/**
 "html:target/cucumber.html"  Basically it's been used everywhere
 */