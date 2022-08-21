package flight_ticket_case.pages;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import flight_ticket_case.util.DriverFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;


public abstract class BasePage {

    protected static WebDriver driver = DriverFactory.getDriver();
    protected WebDriverWait wait;
    protected Actions actions;

    public BasePage(){
        wait = new WebDriverWait(driver, 10L);
        actions = new Actions(driver);
    }

    public WebDriver getDriver(){
        return driver;
    }

    public static void takeScreenshot(String screenShotName){

        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File file = screenshot.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(file, new File("./test-output/failed_tests/"+screenShotName+".png"));
        }
        catch (IOException e){
            System.out.println(e.getMessage()+"---"+e.getCause());
        }

    }

    protected List<WebElement> findElements(By locator){
        List<WebElement> elementList = null;
        try {
            waitElementsVisible(locator);
            elementList =  driver.findElements(locator);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return elementList;
    }

    protected void click(By locator) {
        try {
            waitElementClickable(locator);//try cathle bunları
            driver.findElement(locator).click();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    protected void clickFromList(By locator,int index){
        try {
            driver.findElements(locator).get(index).click();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    protected void sendKeys(By locator, String text) {
        try {
            waitElementClickable(locator);
            driver.findElement(locator).sendKeys(text);
        }
        catch (Exception e){

        }
    }

    protected String getElementText(By locator) {
        String text = null;
        try {
            waitElementVisible(locator);
            text =  driver.findElement(locator).getText();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return text;
    }

    protected boolean isDisplayed(By locator){
        boolean result = false;
        try {
            result = driver.findElement(locator).isDisplayed();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    protected void moveToElement(By locator){
        try {
            waitElementVisible(locator);
            actions.moveToElement(driver.findElement(locator)).perform();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    protected void waitElementVisible(By locator){

        try {
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    protected void waitElementsVisible(By locator){
        try {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    protected void waitElementClickable(By locator){
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    protected void waitElementInvisible(By locator){
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    protected void waitElementPresent(By locator){
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }



}





