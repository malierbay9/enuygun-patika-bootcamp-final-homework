package pages.flights_page;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

public class FlightsPage extends BasePage {

    FilterSection filterSection;
    ResultsSection resultsSection;

    public FlightsPage() {
        filterSection = new FilterSection();
        resultsSection = new ResultsSection();
    }

    public FlightsPage chooseDirectFlight(boolean isDirect) {

        try {
            waitElementInvisible(filterSection.getDisabledFilter());
        }
        catch (TimeoutException e){
            System.out.println(e.getMessage());
        }

        if (isDirect) {
            moveToElement(filterSection.getDirectFlightsLabel());
            click(filterSection.getOnlyDirectFlights());
        } else {
            click(filterSection.getDirectFlightsLabel());
        }

        click(filterSection.getCloseDirectOptions());

        return this;
    }

    public FlightsPage chooseProvider(String provider) {
        click(filterSection.getShowAirlines());
        click(filterSection.getClearSelectedProviders());

        try {

            waitElementPresent(filterSection.getShowAllProviders());
            click(filterSection.getShowAllProviders());

        } catch (TimeoutException e) {
            System.out.println("");
        }

        driver.findElements(filterSection.getProviderList()).stream().forEach(prvdr -> {
            if (prvdr.getText().equals(provider)) {
                //actions.moveToElement(prvdr).perform();
                prvdr.click();
            }
        });

        return this;
    }

    public FlightsPage expandMore() {
        int i = 0;
        By moreActionButton = resultsSection.getMoreActionButton();

        while (driver.findElements(moreActionButton).size() > 0) {

            if (i == driver.findElements(moreActionButton).size()) {
                break;
            }
            else {
                wait.until(ExpectedConditions.visibilityOf(driver.findElements(moreActionButton).get(i)));
                driver.findElements(moreActionButton).get(i).click();
                i++;
            }

        }

        return this;
    }

    public FlightsPage chooseDeparture() {

        waitElementsVisible(resultsSection.getDepartureFlights());
        clickFromList(resultsSection.getDepartureFlights(), 0);

        return this;
    }

    public FlightsPage chooseReturn() {

        waitElementInvisible(resultsSection.getOverlay());
        waitElementsVisible(resultsSection.getReturnFlights());
        clickFromList(resultsSection.getReturnFlights(), 0);

        return this;
    }

    public void selectTicket() {
        waitElementPresent(resultsSection.getReturnFlightRadioBtn());
        click(resultsSection.getChooseTicketBtn());
    }

}
