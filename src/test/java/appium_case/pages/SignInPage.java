package appium_case.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SignInPage extends BasePage{

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "Title")
    private WebElement title;

    @AndroidFindBy(id = "signInEmailTextInput")
    private WebElement userNameField;

    @AndroidFindBy(id = "signInPasswordTextInput")
    private WebElement passwordField;

    @AndroidFindBy(id = "signInButton")
    private WebElement signInBtn;

    @AndroidFindBy(id = "signInErrorView")
    private WebElement errorElement;

    public SignInPage setUserName(String userName){
        waitElementVisible(userNameField);
        userNameField.sendKeys(userName);

        return this;
    }

    public SignInPage setPassword(String password){
        waitElementVisible(passwordField);
        passwordField.sendKeys(password);

        return this;
    }

    public SignInPage signIn(){
        waitElementVisible(signInBtn);
        signInBtn.click();

        return this;
    }

    public String getTitle(){

        return title.getText();

    }

    public String getErrorText(){

        wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(By.id("signInErrorView"),"Error View")));

        return errorElement.getText();

    }



}
