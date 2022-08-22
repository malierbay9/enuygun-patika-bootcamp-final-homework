package flight_ticket_case.pages;


import org.openqa.selenium.By;


//  Seçilen gidiş-dönüş biletlerinin bilgilerinin olduğu sayfadan kullanılacak element locatorları ve gerekli metotları barındırır.

public class FlightInfoPage extends BasePage{

    By departureProvider = By.xpath("//div[@id='flightItemdeparture'][1]/div/div/div[@class='airline-name']");
    By returnProvider = By.xpath("//div[@id='flightItemreturn'][1]/div/div/div[@class='airline-name']");

    public String getDepartureProvider(){
        return getElementText(departureProvider);
    }

    public String getReturnProvider(){
        return getElementText(returnProvider);
    }
}
