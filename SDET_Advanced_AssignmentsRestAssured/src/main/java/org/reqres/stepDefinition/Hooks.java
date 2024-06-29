package org.reqres.stepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks extends BaseClass{
    @Before
    public void setUp() {
        BaseClass.logSetup();
        logger.info("Log4j initiated");
    }
    @Before
    public void beforeTest() {
        logger.info(" Test Started Starting ");
    }
    @After
    public void afterTest() {
        logger.info(" Test Ended ");
    }
}
