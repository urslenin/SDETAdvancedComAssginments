package stepDefinition;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigFileReader;
import utils.Log;

public class BaseClass {

    private static BaseClass baseClass;
    String browserType = ConfigFileReader.getInstance().getProperty("browser");
    String driverPath = System.getProperty("user.dir") + ConfigFileReader.getInstance().getProperty("chromePath");
    private static WebDriver driver;

    private BaseClass() {
        if (browserType.equalsIgnoreCase("chrome")) {
            System.out.println("Launching Chrome browser");
            Log.info("Test Chrome initiated ");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("enable-automation");
            options.addArguments("-remote-allow-origins=*");
            options.addArguments("start-maximized");
            System.setProperty("webdriver.chrome.driver", driverPath);
            driver = new ChromeDriver(options);
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        } else if (browserType.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }
    }

    public static void openPage(String url) {
        driver.get(url);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setUpDriver() {

        if (baseClass == null) {

            baseClass = new BaseClass();
        }
    }

    public static void waitForElementPresent(By locator, int waitTime) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    public static void wait(int sec) throws InterruptedException {
        Thread.sleep(sec*1000);
    }

    public static void moveToElement(By locator) {
        try {
            waitForElementPresent(locator, 5);
            WebElement element = driver.findElement(locator);
            Actions actions = new Actions(driver);
            actions.moveToElement(element).build().perform();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    public static boolean alertIsPresent(int sec) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
            if(wait.until(ExpectedConditions.alertIsPresent())==null) {
                System.out.println("alert was not present");
                return true;
            }
            else {
                System.out.println("alert was present");
                return false;
            }

        } catch (Exception e) {
            e.getStackTrace();
        }
        return true;
    }

    public static String getAlertText() {
        String text ="";
        try {
                text = driver.switchTo().alert().getText();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return text;
    }

    public static void dismissAlert() {
        try {
        driver.switchTo().alert().dismiss();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    public static void acceptOKAlert() {
        try {
            driver.switchTo().alert().accept();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void tearDown() {

        if (driver != null) {
            driver.close();
            driver.quit();
        }
        baseClass = null;
    }

}