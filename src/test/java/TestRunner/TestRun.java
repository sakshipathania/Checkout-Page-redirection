package TestRunner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "." }, // tags= {"@paypal_checkout"},
		plugin = { "pretty", "html:target/site/cucumber-pretty", "json:target/cucumber/cucumber.json",
				"usage:target/usage.jsonx", "junit:target/cucumber.xml" }// ,
//monochrome = true, //display the console output in a proper readable format
//strict = true, //it will check if any step is not defined in step definition file
//dryRun = false //to check the mapping is proper between feature file and step def file
)
public class TestRun {
	@BeforeClass
	public static void beforeClass() throws Exception {
		SetupClass.before_Class();
	}

	@AfterClass
	public static void afterClass() throws Exception {
		SetupClass.after_Class();
	}
}
