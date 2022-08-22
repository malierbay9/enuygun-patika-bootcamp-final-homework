package appium_case.tests;

import appium_case.pages.AddCustomerPage;
import appium_case.pages.CustomersPage;
import appium_case.pages.SignInPage;
import appium_case.pages.WelcomePage;
import appium_case.utils.AndroidListener;
import appium_case.utils.AppiumLocalStarter;
import appium_case.utils.DriverManager;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.testng.annotations.*;
import java.time.LocalDate;

// Testler çalışmadan önce (page objectlerinin instance edilmesi gibi) ve testler bittikten sonra yapılması gereken işlemlerin yapıldığı sınıf.

@Listeners(AndroidListener.class)   //Listener sınıfımızı entegre ediyoruz.
public abstract class BaseTest {

    private WebDriver driver;
    String mail;
    String password;
    String address;
    String name;
    String phoneNumber;
    LocalDate receiveDate;
    SignInPage signInPage;
    WelcomePage welcomePage;
    AddCustomerPage addCustomerPage;
    CustomersPage customersPage;


    @BeforeClass
    public void setUp() {
        this.address = "ankara";
        this.name = "deneme";
        this.phoneNumber = "905005005050";
        this.receiveDate = LocalDate.now();
        try {
            AppiumLocalStarter.start();        //Appium Local starter eğer çalışıyorsa appium u ayrıca açmayınız ya da bunu kapatınız.
        }
        catch (AppiumServerHasNotBeenStartedLocallyException e) {
            System.out.println(e.getMessage());
        }

    }

    @BeforeMethod
    public void setMethod() {

        try {

            driver = DriverManager.setupDriver();   //Testlerin birbirinden bağımsız olması için driverı her test öncesi intance ediyoruz. Test bitince sonlandırıyoruz.
            signInPage = new SignInPage(driver);
            welcomePage = new WelcomePage(driver);
            addCustomerPage = new AddCustomerPage(driver);
            customersPage = new CustomersPage(driver);

        } catch (UnreachableBrowserException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Driver is null. Please initiate the driver.");
        }

    }

    @AfterMethod
    public void afterMethod() {

        try {
            Thread.sleep(2000);
            driver.quit();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }

    @AfterClass
    public void tearDown(){

        AppiumLocalStarter.stop();

    }

}
