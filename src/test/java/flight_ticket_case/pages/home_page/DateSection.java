package flight_ticket_case.pages.home_page;

import lombok.Getter;
import org.openqa.selenium.By;
import java.time.LocalDate;

@Getter
public class DateSection {

    protected static By oneWayCheckBox = By.cssSelector("label.oneWayCheckbox input");
    protected static By departureDayInput = By.id("DepartureDate");
    protected static By returnDayInput = By.id("ReturnDate");
    protected static By monthAndYearOfLeftCalendar = By.xpath("//div[2]/div/div/strong");
    protected static By monthAndYearOfRightCalendar = By.xpath("//div[3]/div/div/strong");
    protected static By calendarForwardBtn = By.xpath("//div[starts-with(@aria-label,'Move f')]");
    protected static By daysOnTheLeft = By.xpath("//div[2]/div/table/tbody/tr/td/div");
    protected static By daysOnTheRight = By.xpath("//div[3]/div/table/tbody/tr/td/div");

    public static LocalDate departureDateCalculator(int days){

        LocalDate departureDate = LocalDate.now().plusDays(days);

        return departureDate;
    }

    public static LocalDate returnDateCalculator(LocalDate departure,int days){

        return departure.plusDays(days);
    }

}
