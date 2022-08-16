package pages;

import org.openqa.selenium.WebDriver;
import util.DriverFactory;

public class BasePage {

    protected static WebDriver driver = DriverFactory.getDriver();

    public BasePage(){

    }

}
