package flight_ticket_case.pages.flights_page;

import lombok.Getter;
import org.openqa.selenium.By;

//Aranan kriterlere sahip uçuşların olduğu kısmına ait element locatorlarını barındırır.

@Getter
public class ResultsSection {

    protected static By fligtPairs = By.cssSelector("div.flightItemContainer");
    protected static By moreActionButton = By.className("moreActionButton");
    protected static By departureFlights = By.xpath("//div[@class='roundTrip departure']/label/div[@data-flight-id]");
    protected static By returnFlights = By.xpath("//div[2]/div[1]/div[1]/div/div/div[1]/div[2]/label");
    protected static By returnFlightRadioBtn = By.xpath("//div[contains(@class,'return')]//label[contains(@class,'active')]/div[1]");
    protected static By chooseTicketBtn = By.id("tooltipTarget_0");
    protected static By overlay = By.className("Overlay show");
    protected static By connectedFlightInfo = By.xpath("//div[@class='flight-summary-time']/div[contains(text(),'Aktarma')]");
    protected static By chosenDepartureDate = By.xpath("//div[2]/div/div/div[1]/div[2]/div[3]");
    protected static By chosenReturnDate = By.xpath("//div[2]/div/div/div[2]/div[2]/div[3]");

}
