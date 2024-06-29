
package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import testbase.BaseClass;
import utils.CommonFunctions;
import utils.ConfigFileReader;


import java.util.List;

public class AjaxLoaderPage extends CommonFunctions {
    private static final Logger logger = Logger.getLogger(AjaxLoaderPage.class);
    WebDriver driver;
    By ajaxLoader = By.xpath("//a[@id='ajax-loader']//h1[text()='AJAX LOADER']");
    By clickMe = By.xpath("//div[@id='myDiv']//p[text()='CLICK ME!']");
    By modelHeader = By.xpath("//div[@id='myModalClick']//h4");
    By modelClose = By.xpath("//div[@id='myModalClick']//button[text()='Close']");

    boolean clickMeFlag = false;
    public AjaxLoaderPage(WebDriver driver){
        this.driver = driver;
    }

    public void homePageLanding() throws InterruptedException {
        setApplicationURL(ConfigFileReader.getInstance().getProperty("url"));
        logger.info("HomePage Loaded");
    }

    public void getAjaxLoaderLink() throws InterruptedException {

        waitForElementPresent(ajaxLoader,10);
        WebElement elementAjaxLoader = getDriver().findElement(ajaxLoader);
        if( !elementAjaxLoader.isDisplayed() ) {
            logger.info("Element is not present" +" AjaxLoader");
            return;
        }
        else{
            moveToElement(ajaxLoader);
            wait(2);
            logger.info("AjaxLoader element is present, Continue with Verification");
            reportInformation("AjaxLoader Element ");
        }

    }

    public void launchAjaxLoader() throws InterruptedException {
        WebElement element = getDriver().findElement(ajaxLoader);
        element.click();
        wait(3);
    }


    public void getClickMeLink() throws InterruptedException {
        Object[] windowHandles = getDriver().getWindowHandles().toArray();
        getDriver().switchTo().window((String) windowHandles[1]);
        wait(4);
        waitForElementPresent(clickMe,10);
        List<WebElement> elements = getDriver().findElements(clickMe);
        if( elements.size()> 0 ) {
            logger.info("CLICK ME "+ "Element Present Continue with Verification");
            clickMeFlag =true;
            reportInformation("ClickMe Element ");
        }
        else if(elements.size()==0){
            logger.info("Element is not present" + "CLICK ME");
        }
    }


    public void launchClickMe() throws InterruptedException {
        if(clickMeFlag) {
            WebElement element = getDriver().findElement(clickMe);
            element.click();
            wait(4);
        }
    }


    public void confirmPopup() throws InterruptedException {
        if(clickMeFlag) {
            if (alertIsPresent(10)) {
                String modelHeaderText = getDriver().findElement(modelHeader).getText();
                if(!modelHeaderText.isEmpty()) {
                    System.out.println("Model Dialog found " + modelHeaderText + " , closing it");
                    reportInformation("Alert Model Popup Element ");
                    getDriver().findElement(modelClose).click();
                    Object[] windowHandles = getDriver().getWindowHandles().toArray();
                    getDriver().switchTo().window((String) windowHandles[1]).close();
                    windowHandles = getDriver().getWindowHandles().toArray();
                    getDriver().switchTo().window((String) windowHandles[0]);
                    getAjaxLoaderLink();
                }
            } else {
                logger.info("Alert is Not Present");
            }
        }
    }

}

