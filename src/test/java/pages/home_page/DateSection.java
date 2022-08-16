package pages.home_page;

import lombok.Getter;
import org.openqa.selenium.By;

import java.time.LocalDate;

@Getter
public class DateSection {

    private By oneWayCheckBox = By.cssSelector("label.oneWayCheckbox");
    private By departureDayInput = By.id("DepartureDate");
    private By monthAndYearOfLeftCalendar = By.xpath("//div[2]/div/div/strong");
    private By monthAndYearOfRightCalendar = By.xpath("//div[3]/div/div/strong");
    private By calendarForwardBtn = By.xpath("//div[starts-with(@aria-label,'Move f')]");
    private By daysOnTheLeft = By.xpath("//div[2]/div/table/tbody/tr/td/div");
    private By daysOnTheRight = By.xpath("//div[3]/div/table/tbody/tr/td/div");

    public LocalDate departureDateCalculator(int days){

        LocalDate departureDate = LocalDate.now().plusDays(days);

        return departureDate;
    }

    public LocalDate returnDateCalculator(LocalDate departure,int days){

        return departure.plusDays(days);
    }

}
