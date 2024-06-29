package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = "",
        features = {"src/test/features/UserUpdate.feature"},
        glue = {"org.reqres.stepDefinition"},
        plugin = { "pretty", "html:test-output/cucumber-reports.html"},
        monochrome = true,
        publish = true
       )

public class TestRunner extends AbstractTestNGCucumberTests {

}