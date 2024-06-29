package testbase;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeTest;
import utils.CommonFunctions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import utils.ConfigFileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseClass extends CommonFunctions {
    private static BaseClass baseClass;
    public static Logger logger = Logger.getLogger(BaseClass.class);
    ConfigFileReader configReader = ConfigFileReader.getInstance();
    String browserType = configReader.getBrowserType();
    String driverPath = System.getProperty("user.dir") + configReader.getDriverPath();
    String applicationUrl = configReader.getApplicationUrl();
    String screenshotFolder = System.getProperty("user.dir") + configReader.getReportsPath();
    String log4jPath = System.getProperty("user.dir") + configReader.getLog4jPath();

   @BeforeSuite
    public void setUp() {
        killChromeDriverInstance();
        String dateYYYYMMMDD = new SimpleDateFormat("yyyy-MMM-dd").format(new Date());
        String dateHHMMSS = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss").format(new Date());
        String screenShotPath = screenshotFolder + dateYYYYMMMDD + "\\" + dateHHMMSS + "\\ScreenShots\\";
        screenPrintSetup(screenShotPath);
        logSetup();
        logger.info("Log4j initiated");
       // logger.info("SetUp completed");
    }

    @BeforeTest
    public void beforeTest() {
        logger.info(" Test Started Starting ");


    }

    @AfterTest
    public void afterTest() {
        logger.info(" Test Ended ");

    }

    private void killChromeDriverInstance() {
        try {
            Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
        } catch (Exception e) {

        }
    }

    public void logSetup() {
        PropertyConfigurator.configure(log4jPath);
    }


}
