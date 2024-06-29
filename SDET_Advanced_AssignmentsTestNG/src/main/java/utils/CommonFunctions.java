package utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import testbase.BaseClass;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.GregorianCalendar;



public class CommonFunctions {
    static public WebDriver driver;
    private static final Logger logger = Logger.getLogger(CommonFunctions.class);
    static String screenShotPath="";
    static String latestScreenshotPath;

    public void setUpDriver(String browserType, String driverPath){
        if( browserType.equalsIgnoreCase("chrome") ){
            System.out.println("launching Chrome browser");
            logger.info("Test ");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("enable-automation");
            options.addArguments("-remote-allow-origins=*");
            options.addArguments("start-maximized");
            System.setProperty("webdriver.chrome.driver", driverPath);
            driver = new ChromeDriver(options);
            driver.manage().timeouts().pageLoadTimeout( Duration.ofSeconds(200));
            driver.manage().timeouts().implicitlyWait( Duration.ofSeconds(ConfigFileReader.getInstance().getImplicitlyWait()));
        } else if( browserType.equalsIgnoreCase("edge") ){
            driver = new EdgeDriver();
        }
    }
    public static WebDriver getDriver() {
        return driver;
    }

    public void setApplicationURL(String url){
        if(url != null && !url.isEmpty())
            driver.get(url);
        else
            System.out.println("Given URL is empty or null");
    }
    public static void takeScreenshot(String testCaseName){
        if(driver == null)
            return;
        String snapFile;
        try {
            String dateFormat = new SimpleDateFormat("dd_MM_yy_HH_mm_ss_SSS").format(new GregorianCalendar().getTime());
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            snapFile = screenShotPath+testCaseName+dateFormat+".png";
            FileHandler.copy(screenshot, new File(snapFile));
            latestScreenshotPath = snapFile;
        }catch(Exception e){
            e.getMessage();
        }
    }
    public static String getCurrentTimeStamp(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }

    public void reportInformation(String testCaseName){
        takeScreenshot(testCaseName);
        String path = "<a href='"+latestScreenshotPath+"'><img src='"+latestScreenshotPath+"' ;height='100' width='100'/a>";
        Reporter.log(path);
    }

    protected void screenPrintSetup(String screenShotPath) {
        try {
            this.screenShotPath = screenShotPath;
            File screenshotFile = new File(screenShotPath);
            if (!(screenshotFile.exists() && screenshotFile.isDirectory()))
                screenshotFile.mkdirs();
        }catch(Exception e){
            e.getStackTrace();
        }

    }

   public void driverWait(int secs) {
        try {
            Thread.sleep(1000 * secs);
        }catch(InterruptedException e){
            e.getStackTrace();
        }
    }

    public String getTitle() {
        try {
            return driver.getTitle();
        }catch(Exception e){
            e.getStackTrace();
        }
        return null;
    }

    public String getText(String xpath_identifier) {
        try {
            return driver.findElement(By.xpath(xpath_identifier)).getText();
        }catch(Exception e){
            e.getStackTrace();
        }
        return null;
    }

    public String getText(By identifier) {
        try {
            return driver.findElement(identifier).getText();
        }catch(Exception e){
            e.getStackTrace();
        }
        return null;
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
}