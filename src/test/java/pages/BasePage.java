package pages;

import org.openqa.selenium.WebDriver;
import util.DriverFactory;
import util.Helper;

public class BasePage {

    protected static WebDriver driver = DriverFactory.getDriver();
    protected Helper helper;

    public BasePage(){
        this.helper = new Helper(driver);
    }

}
