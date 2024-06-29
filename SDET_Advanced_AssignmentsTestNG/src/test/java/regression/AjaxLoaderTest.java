package regression;

import org.apache.log4j.Logger;
import org.testng.annotations.*;
import pages.AjaxLoaderPage;
import testbase.BaseClass;
import utils.ConfigFileReader;

public class AjaxLoaderTest extends BaseClass {
    int numberOfRun=10;
    AjaxLoaderPage ajaxPage = new AjaxLoaderPage(driver);

    @Test(dataProvider = "testData")
    public void verifyAjaxLoadedComponent(String object) throws InterruptedException {
        logger.info(" @Test Method for Iteration " +object);
        ajaxPage.homePageLanding();

        ajaxPage.getAjaxLoaderLink();
        ajaxPage.launchAjaxLoader();
        ajaxPage.getClickMeLink();
        ajaxPage.launchClickMe();
        ajaxPage.confirmPopup();
    }
    @DataProvider(name = "testData")
    public Object[][] dataProviderRun() {

        return new Object[][] { { "Run1" }, { "Run2" },{"Run3"} ,{ "Run5" }, { "Run6" },{"Run6"},{ "Run7" }, { "Run8" },{"Run9"} ,{ "Run10" }};
    }




    @BeforeMethod
    public void beforeMethod() throws InterruptedException {
        logger.info("Before Call");

        String browserType = ConfigFileReader.getInstance().getBrowserType();
        String driverPath = System.getProperty("user.dir") + ConfigFileReader.getInstance().getDriverPath();
        if(getDriver() == null){
            logger.info(" Driver null, create New driver instance");
            setUpDriver(browserType, driverPath);

        }else{
            logger.info(" Driver not null");
        }
    }

    @AfterMethod
       public void afterMethod() {
        System.out.println(" After Call");
        if(getDriver() != null){
            getDriver().quit();
            driver = null;
        }
        logger.info("Test Method End");
    }

}
