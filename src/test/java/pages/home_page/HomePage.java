package pages.home_page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import pages.BasePage;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class HomePage extends BasePage {

    DestinationSection destinationSection;
    DateSection dateSection;

    private By closeCookieBtn = By.xpath("//div[@id='CookieAlert']/button");
    private By findTicketBtn = By.cssSelector("button[data-testid='formSubmitButton']");

    private LocalDate departureDate;
    private LocalDate returnDate;

    public HomePage() {
        destinationSection = new DestinationSection();
        dateSection = new DateSection();
    }

    public HomePage goTo(String url) {
        driver.get(url);

        return this;
    }

    public HomePage closeCookie(){
        try {
            waitElementVisible(closeCookieBtn);
            click(closeCookieBtn);
        }
        catch (TimeoutException e){

        }

        return this;
    }

    public HomePage selectFrom(String from){

        click(destinationSection.getFromTextBox());
        sendKeys(destinationSection.getFromTextBox(), from);
        waitElementVisible(destinationSection.getFirstResultOfFrom());
        click(destinationSection.getFirstResultOfFrom());

        return this;
    }

    public HomePage selectTo(String to) {

        click(destinationSection.getToTextBox());
        sendKeys(destinationSection.getToTextBox(), to);
        waitElementVisible(destinationSection.getFirstResultOfTo());
        click(destinationSection.getFirstResultOfTo());

        return this;
    }

    public void selectDate(int day, String monthAndYear) {

        waitElementVisible(dateSection.getMonthAndYearOfLeftCalendar());

        if (getElementText(dateSection.getMonthAndYearOfLeftCalendar()).equals(monthAndYear)) {

            clickFromList(dateSection.getDaysOnTheLeft(),day-1);

        } else {

            while (!getElementText(dateSection.getMonthAndYearOfRightCalendar()).equals(monthAndYear)) {

                waitElementVisible(dateSection.getCalendarForwardBtn());
                click(dateSection.getCalendarForwardBtn());
            }

            clickFromList(dateSection.getDaysOnTheRight(),day-1);

        }

    }

    public HomePage submitAndSelectDepartureDate(int daysAfterToday) {

        click(dateSection.getDepartureDayInput());
        departureDate = dateSection.departureDateCalculator(daysAfterToday);

        int departureDay = departureDate.getDayOfMonth();

        String departureMonthYear =
                departureDate.getMonth().getDisplayName(TextStyle.FULL, new Locale("tr"))
                + " " + departureDate.getYear();

        selectDate(departureDay, departureMonthYear);

        return this;
    }

    public HomePage submitAndSelectReturnDate(int daysAfterDeparture) {

        if(driver.findElement(dateSection.getOneWayCheckBox()).isSelected()){
            click(dateSection.getOneWayCheckBox());
        }
        else{
            actions.doubleClick(driver.findElement(dateSection.getOneWayCheckBox())).perform();
        }

        returnDate = dateSection.returnDateCalculator(departureDate, daysAfterDeparture);

        int returnDay = returnDate.getDayOfMonth();
        String returnMontYear = returnDate.getMonth().getDisplayName(TextStyle.FULL, new Locale("tr"))
                + " " + returnDate.getYear();

        selectDate(returnDay, returnMontYear);

        return this;
    }

    public void findTicket() {
        click(findTicketBtn);
    }

}
