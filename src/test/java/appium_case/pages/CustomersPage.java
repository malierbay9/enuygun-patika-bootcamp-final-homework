package appium_case.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

//  Uygulamanın müşteriler sayfasında kullanılacak elementleri ve gerekli metotları barındırır.

public class CustomersPage extends BasePage{

    public CustomersPage(WebDriver driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = "//android.widget.RelativeLayout/android.widget.ListView/android.widget.TextView")
    private List<WebElement> customerList;

    @AndroidFindBy(id = "search_button")
    private WebElement searchBtn;

    @AndroidFindBy(id = "search_src_text")
    private WebElement searchField;

    @AndroidFindBy(id = "idTVtext")
    private WebElement customerInfo;

    //Parametrede aldığı telefon numarası değerine göre müşteriyi bulur.
    public CustomersPage findCustomer(String number){

        searchBtn.click();
        waitElementVisible(searchField);
        searchField.sendKeys(number);

        try {
            customerList.stream().forEach(customer->{
                if (customer.getText().equals(number)){
                    customer.click();
                }
            });
        }
        catch (Exception e){

        }

        return this;
    }

    //Parametrede aldığı telefon numarası değerine göre müşterinin var olup olmadığını döndürür
    public boolean checkCustomerExist(String number){

        searchBtn.click();
        waitElementVisible(searchField);
        searchField.sendKeys(number);

        for (WebElement customer : customerList){
            if (customer.getText().equals(number)){
                return true;
            }
        }
        return false;
    }

    public boolean isCustomerInfoContains(String info){
        return customerInfo.getText().contains(info);
    }


}
