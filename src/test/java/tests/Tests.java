package tests;

import org.testng.annotations.Test;

public class Tests extends BaseTest{

    @Test
    public void trial(){

        homePage.selectFrom(from)
                .selectTo(to)
                .submitAndSelectDepartureDate(departureDay)
                .submitAndSelectReturnDate(returnDay)
                .findTicket();

    }

}
