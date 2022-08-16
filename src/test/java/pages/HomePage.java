package pages;

import org.openqa.selenium.By;

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

}
