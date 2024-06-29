package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.ConfigFileReader;

import java.util.List;

public class AjaxLoaderSteps {
    By ajaxLoader = By.xpath("//a[@id='ajax-loader']//h1[text()='AJAX LOADER']");
    By clickMe = By.xpath("//div[@id='myDiv']//p[text()='CLICK ME!']");
    By modelHeader = By.xpath("//div[@id='myModalClick']//h4");
    By modelClose = By.xpath("//div[@id='myModalClick']//button[text()='Close']");

    boolean clickMeFlag = false;

    @Given("User is on application page")
    public void homePageLanding() throws InterruptedException {
        BaseClass.getDriver().get(ConfigFileReader.getInstance().getProperty("url"));

    }

    @When("User confirms Ajax Loader link is present")
    public void getAjaxLoader() throws InterruptedException {

        BaseClass.waitForElementPresent(ajaxLoader,5);
        WebElement elementAjaxLoader = BaseClass.getDriver().findElement(ajaxLoader);
        if( !elementAjaxLoader.isDisplayed() ) {
            System.out.println("Element is not present" +" AjaxLoader");
            return;
        }
        else{
            BaseClass.moveToElement(ajaxLoader);
            BaseClass.wait(2);
            System.out.println("AjaxLoader element is present, Continue with Verification");
        }

    }
    @When("User clicks on Ajax Loader link")
    public void launchAjaxLoader() throws InterruptedException {
        WebElement element = BaseClass.getDriver().findElement(ajaxLoader);
        element.click();
        BaseClass.wait(3);
    }

    @When("User confirms Click Me! is present")
    public void getClickMe() throws InterruptedException {
        Object[] windowHandles = BaseClass.getDriver().getWindowHandles().toArray();
        BaseClass.getDriver().switchTo().window((String) windowHandles[1]);
        BaseClass.wait(4);
        BaseClass.waitForElementPresent(clickMe,10);
        List<WebElement> elements = BaseClass.getDriver().findElements(clickMe);
        if( elements.size()> 0 ) {
            System.out.println("CLICK ME "+ "Element Present Continue with Verification");
            clickMeFlag =true;
        }
        else if(elements.size()==0){
            System.out.println("Element is not present" + "CLICK ME");
        }
    }

    @When("User clicks on Click Me! link")
    public void launchClickMe() throws InterruptedException {
        if(clickMeFlag) {
            WebElement element = BaseClass.getDriver().findElement(clickMe);
            element.click();
            BaseClass.wait(4);
        }
    }

    @Then("User confirms pop-up is present")
    public void confirmPopup() throws InterruptedException {
        if(clickMeFlag) {
            if (BaseClass.alertIsPresent(10)) {
                String modelHeaderText = BaseClass.getDriver().findElement(modelHeader).getText();
                if(!modelHeaderText.isEmpty()) {
                    System.out.println("Model Dialog found " + modelHeaderText + " , closing it");
                    BaseClass.getDriver().findElement(modelClose).click();
                }
            } else {
                System.out.println("Alert is Not Present");
            }
        }
    }

}
