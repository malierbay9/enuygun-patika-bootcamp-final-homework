package util;

import exceptions.WrongDriverTypeException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private static WebDriver driver;
    private static Properties properties = PropertyReader.getProperties();
    private static String browserName = properties.getProperty("drivertype");

    private static int impWait = Integer.parseInt(properties.getProperty("implicitlyWait"));
    private static int pageWait = Integer.parseInt(properties.getProperty("pageLoadTimeout"));
    private static MutableCapabilities capabilities = DriverOptions.getOptions();


    public static WebDriver getDriver(){

        if(browserName.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(capabilities);
        }
        else if(browserName.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver(capabilities);
        }
        else if(browserName.equalsIgnoreCase("edge")){
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver(capabilities);
            driver.manage().window().maximize();
        }
        else{
            throw new WrongDriverTypeException();
        }

        driver.manage().timeouts().implicitlyWait(impWait, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(pageWait,TimeUnit.SECONDS);

        return driver;
    }

    public static void quitDriver() throws InterruptedException {
        Thread.sleep(1000);
        driver.close();
        driver.quit();
    }

}
