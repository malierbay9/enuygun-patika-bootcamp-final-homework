package pages.flights_page;

import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public class FilterSection {
    By directFlightsLabel = By.xpath("//div[@class='custom-controls-stacked']/label[1]");
    By onlyDirectFlights = By.xpath("//label[1]/button[text()='Sadece']");
    By closeDirectOptions = By.xpath("//div[@class='filter-card card'][1]/div[1]");
    By showAirlines = By.cssSelector("div.ctx-filter-airline");
    By clearSelectedProviders = By.xpath("//button[contains(text(),'Hiçbiri')]");
    By providerList = By.cssSelector("span.custom-control-description");
    By showAllProviders = By.xpath("//button[contains(text(),'Tümünü g')]");
    By disabledFilter = By.className("filter-disabled");
}
