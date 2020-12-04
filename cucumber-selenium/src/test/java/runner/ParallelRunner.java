package runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "html:target/Reports/Cuke-Html/Parallel",
		"json:target/Reports/Cuke-Json/parallel_cucumber.json" }, features = {
				"src/test/resource" }, glue = "stepDefinitions", tags = { "@pitchfork" })

public class ParallelRunner {

}
