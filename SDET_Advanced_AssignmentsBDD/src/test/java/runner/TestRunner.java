package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = "",
        features = {"src/test/resources/features/AjaxLoader.feature"},
        glue = {"stepDefinition"},
        plugin = { "pretty", "html:target/cucumber-reports.html"},
        monochrome = true,
        publish = true)

public class TestRunner extends AbstractTestNGCucumberTests {

}