package tests;

import org.testng.annotations.Test;

public class Tests extends BaseTest{

    @Test
    public void trial(){

        homePage.goTo(url)
                .selectFrom(from)
                .selectTo(to)
                .submitAndSelectDepartureDate(departureDay)
                .submitAndSelectReturnDate(returnDay)
                .findTicket();

        flightsPage.chooseDirectFlight(isDirect)
                    .chooseProvider(provider)
                    .expandMore()
                    .chooseDeparture()
                    .chooseReturn()
                    .selectTicket();

    }

}
