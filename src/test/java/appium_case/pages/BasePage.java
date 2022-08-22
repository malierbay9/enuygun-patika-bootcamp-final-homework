package appium_case.pages;


import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//Bu sınıf diğer page object sınıflarından extend edilir.
//Kullanacağımız driverın parametre olarak alındığı sınıftır.


public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,10L);
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);    //Bu işlemi burada yapıyoruz. Böylece alt sınıflarda tekrar yazmamıza gerek kalmaz.
    }

    public void waitElementVisible(WebElement element){

        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }




}
