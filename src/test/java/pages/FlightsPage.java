package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FlightsPage extends BasePage{

    By moreActionButton = By.className("moreActionButton");
    By departureFlights = By.xpath("//div[@class='roundTrip departure']/label/div[@data-flight-id]");
    //By returnFlights = By.xpath("//div[@class='roundTrip return active']");
    By returnFlights = By.xpath("//div[2]/div[1]/div[1]/div/div/div[1]/div[2]/label");
    By returnFlightRadioBtn = By.xpath("//div[contains(@class,'return')]//label[contains(@class,'active')]/div[1]");
    By chooseTicketBtn = By.id("tooltipTarget_0");
    By directFlightsLabel = By.xpath("//div[@class='custom-controls-stacked']/label[1]");
    By onlyDirectFlights = By.xpath("//label[1]/button[text()='Sadece']");
    By closeDirectOptions = By.xpath("//div[@class='filter-card card'][1]/div[1]");
    By showAirlines = By.cssSelector("div.ctx-filter-airline");
    By clearSelectedProviders = By.xpath("//button[contains(text(),'Hiçbiri')]");
    By providerList = By.cssSelector("span.custom-control-description");
    By showAllProviders = By.xpath("//button[contains(text(),'Tümünü g')]");
    By overlay = By.className("Overlay show");
    By disabledFilter = By.className("filter-disabled");

    public FlightsPage chooseDirectFlight(boolean isDirect) {

        wait.until(ExpectedConditions.invisibilityOfElementLocated(disabledFilter));

        if (isDirect) {
            actions.moveToElement(driver.findElement(directFlightsLabel)).perform();
            click(onlyDirectFlights);
        } else {
            click(directFlightsLabel);
        }

        click(closeDirectOptions);

        return this;
    }

    public FlightsPage chooseProvider(String provider) {
        click(showAirlines);
        click(clearSelectedProviders);

        try {

            wait.until(ExpectedConditions.presenceOfElementLocated(showAllProviders));
            click(showAllProviders);

        }
        catch (TimeoutException e) {
            System.out.println("");
        }

        driver.findElements(providerList).stream().forEach(prvdr -> {
            if (prvdr.getText().equals(provider)) {
                //actions.moveToElement(prvdr).perform();
                prvdr.click();
            }
        });

        return this;
    }

    public FlightsPage expandMore() {
        int i = 0;

        while (driver.findElements(moreActionButton).size() > 0) {

            if (i == driver.findElements(moreActionButton).size()) {
                break;
            } else {
                wait.until(ExpectedConditions.visibilityOf(driver.findElements(moreActionButton).get(i)));
                driver.findElements(moreActionButton).get(i).click();
                i++;
            }

        }

        return this;
    }

    public FlightsPage chooseDeparture() {

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(departureFlights));
        clickFromList(departureFlights, 0);

        return this;
    }

    public FlightsPage chooseReturn() {

        wait.until(ExpectedConditions.invisibilityOfElementLocated(overlay));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(returnFlights));
        clickFromList(returnFlights, 0);

        return this;
    }

    public void selectTicket() {
        wait.until(ExpectedConditions.presenceOfElementLocated(returnFlightRadioBtn));
        click(chooseTicketBtn);
    }

}
