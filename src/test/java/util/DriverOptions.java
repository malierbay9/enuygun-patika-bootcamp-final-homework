package util;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class DriverOptions {

    private static ChromeOptions chromeOptions = new ChromeOptions();
    private static FirefoxOptions firefoxOptions = new FirefoxOptions();
    private static EdgeOptions edgeOptions = new EdgeOptions();

    private static Properties properties = PropertyReader.getProperties();
    private static String driverType = properties.getProperty("drivertype");

    private static List<String> args = Arrays.asList(properties.get("arguments").toString().split(","));

    public static MutableCapabilities getOptions(){

        if (driverType.equalsIgnoreCase("chrome")){
            chromeOptions.addArguments(args);
            return chromeOptions;
        }
        else if (driverType.equalsIgnoreCase("firefox")){
            firefoxOptions.addArguments(args);
            return firefoxOptions;
        }
        else if (driverType.equalsIgnoreCase("edge")){
            return edgeOptions;
        }

        return null;
    }

}
