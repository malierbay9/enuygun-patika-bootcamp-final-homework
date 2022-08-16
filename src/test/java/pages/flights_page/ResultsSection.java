package pages.flights_page;

import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public class ResultsSection {

    By moreActionButton = By.className("moreActionButton");
    By departureFlights = By.xpath("//div[@class='roundTrip departure']/label/div[@data-flight-id]");
    By returnFlights = By.xpath("//div[2]/div[1]/div[1]/div/div/div[1]/div[2]/label");
    By returnFlightRadioBtn = By.xpath("//div[contains(@class,'return')]//label[contains(@class,'active')]/div[1]");
    By chooseTicketBtn = By.id("tooltipTarget_0");
    By overlay = By.className("Overlay show");

}
