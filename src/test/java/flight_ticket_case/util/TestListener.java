package flight_ticket_case.util;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import flight_ticket_case.pages.BasePage;
import flight_ticket_case.tests.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.IOException;

//Projede kullanacağımız listener sınıfı
//Metotlara loglama ve raporlama toolları entegre edilmiştir.

public class TestListener implements ITestListener {

    Logger logger = LogManager.getLogger(BaseTest.class);
    ExtentHtmlReporter htmlReporter;
    ExtentReports extent;
    ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        logger.info("Tests are starting !");
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/flight_case_reports.html");
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
        String failedTest = result.getName();
        String screenshotsDirectory = "./test-output/failed_test/";
        logger.error(result.getName()+" FAILED !");

        BasePage.takeScreenshot(failedTest);    //test başarısız olduğunda ekran görüntüsü alma

        test = extent.createTest(failedTest);

        try {
            test.log(Status.FAIL,result.getName()+" failed.")
                    .addScreenCaptureFromPath(screenshotsDirectory+failedTest+".png")   //alınan ekran görüntüsünü rapora ekleme
                    .info(result.getMethod().getDescription());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.info(result.getName()+" SKIPPED !");
        test = extent.createTest(result.getName());
        test.log(Status.SKIP,result.getName()+" skipped.")
                .info(result.getMethod().getDescription());
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Tests are finished !");
        extent.flush();
    }

}
