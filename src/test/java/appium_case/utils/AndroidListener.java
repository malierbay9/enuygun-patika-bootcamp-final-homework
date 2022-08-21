package appium_case.utils;

import appium_case.tests.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class AndroidListener implements ITestListener {

    Logger logger = LogManager.getLogger(BaseTest.class);
    ExtentHtmlReporter htmlReporter;
    ExtentReports extent;
    ExtentTest test;


    @Override
    public void onStart(ITestContext context) {
        logger.info("Tests are starting !");
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/appium_extent.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info(result.getName()+" test is running !");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info(result.getName()+" PASSED !");
        test = extent.createTest(result.getName());
        test.log(Status.PASS,result.getName()+" passed.")
                .info(result.getMethod().getDescription());
    }

    @Override
    public void onTestFailure(ITestResult result) {

        logger.error(result.getName()+" FAILED !");

            test.log(Status.FAIL,result.getName()+" failed.")
                    .info(result.getMethod().getDescription());

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.info(result.getName()+" SKIPPED !");
        extent.createTest(result.getName())
                .log(Status.SKIP,result.getName()+" skipped.")
                .info(result.getMethod().getDescription());

    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Tests are finished !");
        extent.flush();
    }

}
