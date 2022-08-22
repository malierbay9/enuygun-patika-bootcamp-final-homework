package appium_case.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

//  Uygulamanın giriş yaptıktan sonra gelen hoş geldiniz sayfasında kullanılacak elementleri ve gerekli metotları barındırır.

public class WelcomePage extends BasePage{

    public WelcomePage(WebDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "signUpPageTitle")
    private WebElement welcomeTitle;

    @AndroidFindBy(id = "searchCustomerInfo")
    private WebElement searchCustomer;

    @AndroidFindBy(id = "newCustomerInfo")
    private WebElement createCustomer;

    public void addNewCustomer(){
        waitElementVisible(createCustomer);
        createCustomer.click();
    }

    public void searchCustomer(){
        waitElementVisible(searchCustomer);
        searchCustomer.click();
    }

    public boolean isWelcomeTitleDisplayed(){
        waitElementVisible(welcomeTitle);
        return welcomeTitle.isDisplayed();

    }



}
