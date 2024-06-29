package org.reqres.stepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import utils.CommonFunctions;
import utils.ConfigFileReader;

public class BaseClass extends CommonFunctions {
    private static BaseClass baseClass;
    public static Logger logger = Logger.getLogger(BaseClass.class);
    static ConfigFileReader configReader = ConfigFileReader.getInstance();
    static String log4jPath = System.getProperty("user.dir") + configReader.getLog4jPath();

    public static void logSetup() {
        PropertyConfigurator.configure(log4jPath);
    }


}
