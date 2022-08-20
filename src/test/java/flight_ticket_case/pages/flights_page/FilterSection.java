package flight_ticket_case.pages.flights_page;

import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public class FilterSection {
    protected static By directFlightsLabel = By.xpath("//div[@class='custom-controls-stacked']/label[1]");
    protected static By onlyDirectFlights = By.xpath("//label[1]/button[text()='Sadece']");
    protected static By closeDirectOptions = By.xpath("//div[@class='filter-card card'][1]/div[1]");
    protected static By showAirlines = By.cssSelector("div.ctx-filter-airline");
    protected static By clearSelectedProviders = By.xpath("//button[contains(text(),'Hiçbiri')]");
    protected static By providersInFilter = By.cssSelector("span.custom-control-description");
    protected static By showAllProviders = By.xpath("//button[contains(text(),'Tümünü g')]");
    protected static By disabledFilter = By.className("filter-disabled");
}
