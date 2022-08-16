package pages.home_page;

import org.openqa.selenium.By;
import pages.BasePage;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class HomePage extends BasePage {

    DestinationSection destinationSection;
    DateSection dateSection;

    private By findTicketBtn = By.cssSelector("button[data-testid='formSubmitButton']");

    private LocalDate departureDate;
    private LocalDate returnDate;

    public HomePage() {
        destinationSection = new DestinationSection();
        dateSection = new DateSection();
    }

    public HomePage selectFrom(String from){

        sendKeys(destinationSection.getFromTextBox(), from);
        waitElementVisible(destinationSection.getFirstResultOfFrom());
        click(destinationSection.getFirstResultOfFrom());

        return this;
    }

    public HomePage selectTo(String to) {

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

        click(dateSection.getOneWayCheckBox());
        returnDate = dateSection.returnDateCalculator(departureDate, daysAfterDeparture);

        int returnDay = returnDate.getDayOfMonth();

        String returnMontYear =
                returnDate.getMonth().getDisplayName(TextStyle.FULL, new Locale("tr"))
                + " " + returnDate.getYear();

        selectDate(returnDay, returnMontYear);

        return this;
    }

    public void findTicket() {
        click(findTicketBtn);
    }

}
