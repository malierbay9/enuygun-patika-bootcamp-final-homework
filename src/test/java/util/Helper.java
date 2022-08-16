package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;


public class Helper {

    WebDriver driver;
    Actions actions;
    WebDriverWait wait;

    public Helper(WebDriver driver){
        this.driver = driver;
        actions = new Actions(this.driver);
        wait = new WebDriverWait(this.driver, 20L);
    }

    public void hoverToElement(By element){
        actions.moveToElement(driver.findElement(element)).perform();
    }

    public void waitElementVisible(By element){
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(element)));
    }

    public void clickAndSendKeys(By element,String keysToSend){
        WebElement element1 = driver.findElement(element);
        //element1.click();
        element1.sendKeys(keysToSend);
    }

    public LocalDate departureDateCalculator(int days){

        LocalDate departureDate = LocalDate.now().plusDays(days);

        return departureDate;
    }

    public LocalDate returnDateCalculator(LocalDate departure,int days){

        return departure.plusDays(days);
    }

}
