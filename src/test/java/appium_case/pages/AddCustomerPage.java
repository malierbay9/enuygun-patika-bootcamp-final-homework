package appium_case.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.time.LocalDate;

//  Uygulamanın müştreri ekleme sayfasında kullanılacak elementleri ve gerekli metotları barındırır.

public class AddCustomerPage extends BasePage{

    public AddCustomerPage(WebDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "customertelnumber")
    private WebElement customerNumber;

    @AndroidFindBy(id = "customernamesurname")
    private WebElement customerName;

    @AndroidFindBy(id = "customeraddress")
    private WebElement customerAdress;

    @AndroidFindBy(id = "in_date")
    private WebElement deliverDate;

    @AndroidFindBy(id = "in_received_date")
    private WebElement receiveDate;

    @AndroidFindBy(id = "customerInfoSave")
    private WebElement saveBtn;

    public AddCustomerPage setNumber(String number) {
        customerNumber.sendKeys(number);
        return this;
    }

    public AddCustomerPage setName(String name){
        customerName.sendKeys(name);
        return this;
    }

    public AddCustomerPage setAdress(String adress){
        customerAdress.sendKeys(adress);
        return this;
    }

    public AddCustomerPage setDate(LocalDate date){
        driver.navigate().back();
        receiveDate.sendKeys(date.toString());
        deliverDate.sendKeys(date.plusDays(10).toString());

        return this;
    }

    public void saveCustomer(){
        saveBtn.click();
    }


}
