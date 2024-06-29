
package stepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    @Before
    public void setUp() {
        BaseClass.setUpDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        BaseClass.tearDown();
    }
}