package utils;
import java.io.File;
import java.io.FileNotFoundException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class TestListener implements ITestListener {

    private static final Logger log = LogManager.getLogger(TestListener.class);
    private String fileSeperator ="\\";
    public static final String SRC = "test-output/custom-report.html";
    private String testReportPath = System.getProperty("user.dir") + fileSeperator + "TestReport";
    private String reportsPath = testReportPath + fileSeperator + "screenshots";
    @Override
    public void onTestFailure(ITestResult result) {
        log.info("***** Error " +result.getMethod().getMethodName() + " failed!");
        takeScreenShot(result);
    }

    public void takeScreenShot(ITestResult result) {

        log.info("*** Taking screenshot for " + result.getMethod().getMethodName() + "...");
        ITestContext context = result.getTestContext();
        WebDriver driver = (WebDriver) context.getAttribute("driver");
        Reporter.setCurrentTestResult(result);

        String targetLocation ;
        String testClassName = getTestClassName(result.getInstanceName()).trim();
        String timeStamp = CommonFunctions.getCurrentTimeStamp("yyyyMMddHHmmss"); // get timestamp
        String testMethodName = result.getName().trim();
        String screenShotName = testMethodName +"_"+ timeStamp + ".png";

        log.info("Screenshots reports path - " + reportsPath);
        try {
            //Screen shot folder
            File file = new File(reportsPath + fileSeperator + testClassName);
            if (!file.exists()) {
                if (file.mkdirs()) {
                    log.info("Directory: " + file.getAbsolutePath() + " is created!");
                } else {
                    log.info("Failed to create directory: " + file.getAbsolutePath());
                }
            }

            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            targetLocation = reportsPath + fileSeperator + testClassName + fileSeperator + screenShotName;// define
            // location
            File targetFile = new File(targetLocation);
            log.info("Screen shot file location - " + screenshotFile.getAbsolutePath());
            log.info("Target File location - " + targetFile.getAbsolutePath());
            FileHandler.copy(screenshotFile, targetFile);

            Reporter.log("<a href=" + targetLocation + "> <img class= \"img-responsive\" width='304' height='236' src=" + targetLocation + "> </a>");
            Reporter.setCurrentTestResult(null);

        } catch (FileNotFoundException e) {
            log.info("File not found exception occurred while taking screenshot " + e.getMessage());
        } catch (Exception e) {
            log.info("An exception occurred while taking screenshot " + e.getCause());
        }
    }
    public void onFinish(ITestContext context) {}

    public void onTestStart(ITestResult result) {   }

    public void onTestSuccess(ITestResult result) {
        if(result.isSuccess()) {
            log.info("***** Success "+result.getName()+" test has passed *****");
            takeScreenShot(result);
        }
    }

    public void onTestSkipped(ITestResult result) {
        if(result.wasRetried()) {
            log.info("***** Retried "+result.getName()+" test has failed *****");
            takeScreenShot(result);
        }
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {   }

    public void onStart(ITestContext context) {   }

    public String getTestClassName(String testName) {
        String[] reqTestClassname = testName.split("\\.");
        int i = reqTestClassname.length - 1;
        log.info("Required Test Name : " + reqTestClassname[i]);
        return reqTestClassname[i];
    }
}