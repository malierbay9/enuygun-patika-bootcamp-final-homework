package pages;

import org.openqa.selenium.By;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class HomePage extends BasePage{

    private By fromTextBox = By.id("OriginInput");
    private By firstResultOfFrom = By.id("react-autowhatever-OriginInput-section-0-item-0");
    private By toTextBox = By.id("DestinationInput");
    private By firstResultOfTo = By.id("react-autowhatever-DestinationInput-section-0-item-0");

    private By oneWayCheckBox = By.cssSelector("label.oneWayCheckbox");
    private By departureDayInput = By.id("DepartureDate");
    private By monthAndYearOfLeftCalendar = By.xpath("//div[2]/div/div/strong");
    private By monthAndYearOfRightCalendar = By.xpath("//div[3]/div/div/strong");
    private By calendarForwardBtn = By.xpath("//div[starts-with(@aria-label,'Move f')]");
    private By daysOnTheLeft = By.xpath("//div[2]/div/table/tbody/tr/td/div");
    private By daysOnTheRight = By.xpath("//div[3]/div/table/tbody/tr/td/div");
    private By findTicketBtn = By.cssSelector("button[data-testid='formSubmitButton']");

    private LocalDate departureDate;
    private LocalDate returnDate;

    public HomePage selectFrom(String from){

        helper.clickAndSendKeys(fromTextBox, from);
        helper.waitElementVisible(firstResultOfFrom);
        driver.findElement(firstResultOfFrom).click();

        return this;
    }

    public HomePage selectTo(String to) {

        helper.clickAndSendKeys(toTextBox, to);
        helper.waitElementVisible(firstResultOfTo);
        driver.findElement(firstResultOfTo).click();

        return this;
    }

    public void selectDate(int day, String monthAndYear) {



    }

    public HomePage submitDepartureDate(int daysAfterToday){
        driver.findElement(departureDayInput).click();
        departureDate = helper.departureDateCalculator(daysAfterToday);
        return this;
    }

    public HomePage selectDepartureDate() {

        int departureDay = departureDate.getDayOfMonth();
        String departureMonthYear = departureDate.getMonth().getDisplayName(TextStyle.FULL, new Locale("tr"))
                + " " + departureDate.getYear();

        helper.waitElementVisible(monthAndYearOfLeftCalendar);

        if (driver.findElement(monthAndYearOfLeftCalendar).getText().equals(departureMonthYear)) {

            driver.findElements(daysOnTheLeft).get(departureDay-1).click();

        } else {

            while (!driver.findElement(monthAndYearOfRightCalendar).getText().equals(departureMonthYear)) {

                helper.waitElementVisible(calendarForwardBtn);
                driver.findElement(calendarForwardBtn).click();
            }

            driver.findElements(daysOnTheRight).get(departureDay-1);

        }

        return this;
    }

    public HomePage submitReturnDate(int daysAfterDeparture){

        driver.findElement(oneWayCheckBox).click();
        returnDate = helper.returnDateCalculator(departureDate, daysAfterDeparture);

        return this;
    }

    public HomePage selectReturnDate() {

        int returnDay = returnDate.getDayOfMonth();
        String returnMontYear = returnDate.getMonth().getDisplayName(TextStyle.FULL, new Locale("tr"))
                + " " + returnDate.getYear();

        helper.waitElementVisible(monthAndYearOfLeftCalendar);

        if (driver.findElement(monthAndYearOfLeftCalendar).getText().equals(returnMontYear)) {

            driver.findElements(daysOnTheLeft).get(returnDay-1).click();

        } else {

            while (!driver.findElement(monthAndYearOfRightCalendar).getText().equals(returnMontYear)) {

                helper.waitElementVisible(calendarForwardBtn);
                driver.findElement(calendarForwardBtn).click();
            }

            driver.findElements(daysOnTheRight).get(returnDay-1);

        }

        return this;
    }

    public void findTicket() {
        driver.findElement(findTicketBtn).click();
    }

}
