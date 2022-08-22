package appium_case.utils;

import io.appium.java_client.android.AndroidDriver;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;


//Android driverımızı elde ettiğimiz sınıftır.
@Data
public class DriverManager {
    private static DesiredCapabilities capabilities;
    private static WebDriver driver;
    private static URL url ;

    public DriverManager(){

    }

    public static WebDriver setupDriver() {

        try {
            String capFilePath = System.getProperty("user.dir")+"/src/test/resources/appium_capabilities/testPhoneCaps.json";
            url=new URL("http://127.0.0.1:4723/wd/hub");
            capabilities = CapabilitySettings.getDesiredCapsFromJson(capFilePath);
            driver = new AndroidDriver<>(url,capabilities);
        }
        catch (MalformedURLException e){
            System.out.println(e.getMessage());
        }

        return driver;
    }





}
