package flight_ticket_case.pages.home_page;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import flight_ticket_case.pages.BasePage;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import static flight_ticket_case.pages.home_page.DestinationSection.*;
import static flight_ticket_case.pages.home_page.DateSection.*;

//Ana sayfanın page object sınıfıdır. Testlerde kullanılacak metotları ve ana sayfadaki gerekli elementlerin locatolarının bir kısmını içerir.
//Locatorların geri kalanları DateSection ve DestinationSection sınıflarının static olarak import edilmesiyle kullanır.

public class HomePage extends BasePage {

    private By closeCookieBtn = By.xpath("//div[@id='CookieAlert']/button");
    private By findTicketBtn = By.cssSelector("button[data-testid='formSubmitButton']");
    private By errorIndicator = By.xpath("//div[@class='D_FSF__popover']");
    private By connectedFlightChoice = By.cssSelector("label[for='transitFilter']");

    private LocalDate departureDate;
    private LocalDate returnDate;

    public HomePage goTo(String url) {
        driver.get(url);

        return this;
    }

    public HomePage closeCookie(){

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(closeCookieBtn));
            click(closeCookieBtn);
        }
        catch (Exception e){
            System.out.println("");
        }

        return this;
    }

    public HomePage clickFrom(){
        click(fromTextBox);
        return this;
    }

    //nereden gidileceğini seçer
    public HomePage selectFrom(String from){

        sendKeys(fromTextBox, from);
        waitElementVisible(firstResultOfFrom);
        click(firstResultOfFrom);

        return this;
    }

    public HomePage clickTo(){
        click(toTextBox);
        return this;
    }

    //nereye gidileceğini seçer
    public HomePage selectTo(String to) {

        sendKeys(toTextBox, to);
        waitElementVisible(firstResultOfTo);
        click(firstResultOfTo);

        return this;
    }


    /*      Parametresinde aldığı monthAndYear Stringini , sayfada açılan takvimlerin önce soldaki olanı için
    *   üst kısmında yer alan Ay ve Yıl Texti ile kıyaslar. Aynı ise parametresinde aldığı 'day' değerine eşit
    *   gün elementine tıklayıp tarihi seçer. Eğer sol takvim doğru değilse sağdaki takvim için aynı işlemi tekrarlar.
    *   Bu da doğru değilse Ay ve Yıl doğru olana kadar takvimi ilerletir ve tarihi seçer.   */
    public void selectDate(int day, String monthAndYear) {

        waitElementVisible(monthAndYearOfLeftCalendar);

        if (getElementText(monthAndYearOfLeftCalendar).equals(monthAndYear)) {

            clickFromList(daysOnTheLeft, day - 1);

        } else {

            while (!getElementText(monthAndYearOfRightCalendar).equals(monthAndYear)) {

                waitElementVisible(calendarForwardBtn);
                click(calendarForwardBtn);
            }

            clickFromList(daysOnTheRight, day - 1);

        }

    }

    public HomePage openDepartureCalendar(){
        click(departureDayInput);
        return this;
    }

    public HomePage submitAndSelectDepartureDate(int daysAfterToday) {

        departureDate = departureDateCalculator(daysAfterToday);    //departureDateCalculator bize YYYY-MM-DD formatında tarih döndürür.
                                                                    //Bu tarihi kullanabileceğimiz formata çevirip 'Gün' ve 'Ay-Yıl' içeren 2 değişken elde ederiz.
        int departureDay = departureDate.getDayOfMonth();
        String departureMonthYear = departureDate.getMonth().getDisplayName(TextStyle.FULL, new Locale("tr"))
                + " " + departureDate.getYear();

        selectDate(departureDay, departureMonthYear);       //'Gün' ve 'Ay-Yıl' içeren değişkenleri selectDate() metoduna vererek tarihi seçeriz.

        return this;
    }

    public HomePage openReturnCalendar(){
        click(returnDayInput);
        return this;
    }

    //Dönüş tarihi seçimi de aynı gidiş tarihi seçimi gibidir.
    public HomePage submitAndSelectReturnDate(int daysAfterDeparture) {

        //Dönüş takvimini açmak için 'Tek-Yön' butonunu kapatırız.
        if(driver.findElement(oneWayCheckBox).isSelected()){
            click(oneWayCheckBox);
        }
        else{
            actions.doubleClick(driver.findElement(oneWayCheckBox)).perform();
        }

        returnDate = returnDateCalculator(departureDate, daysAfterDeparture);

        int returnDay = returnDate.getDayOfMonth();
        String returnMontYear = returnDate.getMonth().getDisplayName(TextStyle.FULL, new Locale("tr"))
                + " " + returnDate.getYear();

        selectDate(returnDay, returnMontYear);

        return this;
    }

    public HomePage chooseNonConnectedFlights(){
        click(connectedFlightChoice);
        return this;
    }

    public void findTicket() {
        click(findTicketBtn);
    }

    public boolean checkFromSuggestions(){
        return isDisplayed(fromSuggestionTab);
    }

    public boolean checkToSuggestions(){
        return isDisplayed(toSuggestionTab);
    }

    public boolean checkDepartureCalendar(){
        return isDisplayed(monthAndYearOfLeftCalendar);
    }

    public boolean checkDepartureCalendarSwitching(){
        String firstValue = getElementText(monthAndYearOfRightCalendar);
        click(calendarForwardBtn);
        return !firstValue.equals(getElementText(monthAndYearOfRightCalendar));
    }

    public boolean checkReturnCalendar(){
        return isDisplayed(monthAndYearOfLeftCalendar);
    }

    public boolean checkDepartureReturnSwitching(){
        String firstValue = getElementText(monthAndYearOfRightCalendar);
        click(calendarForwardBtn);
        return !firstValue.equals(getElementText(monthAndYearOfRightCalendar));
    }

    public boolean checkErrorElementsShown(){
        return isDisplayed(errorIndicator);
    }

    public String getDepartureDate(){
        return driver.findElement(departureDayInput).getAttribute("value");
    }

    public String getReturnDate(){
        return driver.findElement(returnDayInput).getAttribute("value");
    }

}
