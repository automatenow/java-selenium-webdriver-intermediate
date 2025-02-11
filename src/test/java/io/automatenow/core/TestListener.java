package io.automatenow.core;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * @author Marco A. Cruz
 */
public class TestListener extends BasePage implements ITestListener {
    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        String methodName = iTestResult.getName();

        // TODO don't do this! (code duplication!)
//        TakesScreenshot screenshot = (TakesScreenshot) driver;
//        File file = screenshot.getScreenshotAs(OutputType.FILE);
//        try {
//            FileUtils.copyFile(file, new File("./screenshots/" + methodName + ".png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        takeScreenshot(methodName);
        log.error("Test '" + methodName + "' has failed and a screenshot was taken.");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
