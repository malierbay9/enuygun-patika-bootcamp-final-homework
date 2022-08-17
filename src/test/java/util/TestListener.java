package util;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.BaseTest;

public class TestListener implements ITestListener {

    Logger logger = LogManager.getLogger(BaseTest.class);

    @Override
    public void onStart(ITestContext context) {
        logger.info("Tests are starting !");
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info(result.getName()+" test is running !");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info(result.getName()+" PASSED !");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error(result.getName()+" FAILED !");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.info(result.getName()+" SKIPPED !");
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Tests are finished !");
    }

}
