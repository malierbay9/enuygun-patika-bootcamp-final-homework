package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.DriverFactory;


public class BasePage {

    protected static WebDriver driver = DriverFactory.getDriver();
    protected WebDriverWait wait;
    protected Actions actions;

    public BasePage(){
        wait = new WebDriverWait(driver, 10L);
        actions = new Actions(driver);
    }

    protected void click(By locator) {
        waitElementClickable(locator);
        driver.findElement(locator).click();
    }

    protected void clickFromList(By locator,int index){
        driver.findElements(locator).get(index).click();
    }

    protected void sendKeys(By locator, String text) {
        waitElementClickable(locator);
        driver.findElement(locator).sendKeys(text);
    }

    protected String getElementText(By locator) {
        waitElementVisible(locator);
        return driver.findElement(locator).getText();
    }

    public void waitElementVisible(By locator){
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
    }

    public void waitElementClickable(By locator){
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }



}
