package flight_ticket_case.tests;


import org.testng.annotations.Test;
import static org.testng.Assert.*;
import static org.testng.Assert.assertEquals;

//Flight Ticket Case için testlerin bulunduğu sınıf

public class Tests extends BaseTest{

    @Test(priority = 0,description = "checks opened page is correct.")
    public void checkIfPageCorrect(){
        homePage.goTo(url);

        assertEquals(homePage.getDriver().getCurrentUrl(),url);
    }

    @Test(priority = 1,description = "checks departure suggestion tab shown when clicked.")
    public void checkIfDepartureSuggestionTabShown(){
        homePage.goTo(url).clickFrom();

        assertTrue(homePage.checkFromSuggestions());
    }

    @Test(priority = 2,description = "checks return suggestion tab shown when clicked.")
    public void checkIfReturnSuggestionTabShown(){
        homePage.goTo(url).clickTo();

        assertTrue(homePage.checkToSuggestions());
    }

    @Test(priority = 3,description = "Checks departure calendar shown when clicked to departure input.")
    public void checkIfDepartureCalendarShown(){
        homePage.goTo(url).closeCookie().openDepartureCalendar();

        assertTrue(homePage.checkDepartureCalendar());
    }

    @Test(priority = 4,description = "Checks return calendar shown when clicked to departure input.")
    public void checkIfReturnCalendarShown(){
        homePage.goTo(url).openReturnCalendar();

        assertTrue(homePage.checkReturnCalendar());
    }

    @Test(priority = 5,description = "Checks departure calendar is switching when clicked to calendar forward button.")
    public void checkIfDepartureCalendarSwitching(){
        homePage.goTo(url).closeCookie().openDepartureCalendar();

        assertTrue(homePage.checkDepartureCalendarSwitching());
    }

    @Test(priority = 6,description = "Checks return calendar is switching when clicked to calendar forward button.")
    public void checkIfReturnCalendarSwitching(){
        homePage.goTo(url).openReturnCalendar();

        assertTrue(homePage.checkDepartureReturnSwitching());
    }

    @Test(priority = 7,description = "Checks if there is an error feedback when user entered same location in both departure and return.")
    public void checkIfErrorsWhenDepartureReturnLocatedSame(){

        homePage.goTo(url)
                .clickFrom().selectFrom(from)
                .clickTo().selectTo(from)
                .findTicket();

        assertTrue(homePage.checkErrorElementsShown());
    }

    @Test(priority = 8,description = "Checks if there is an error feedback when user tries to find ticket without entering any input.")
    public void checkIfErrorsWhenTryingFindTicketWithoutLocations(){

        homePage.goTo(url).findTicket();

        assertTrue(homePage.checkErrorElementsShown());

    }

    @Test(priority = 9,description = "Checks if there is any flight with entered location and date info.")
    public void checkIfFlightsExist(){
        homePage.goTo(url)
                .closeCookie()
                .clickFrom().selectFrom(from)
                .clickTo().selectTo(to)
                .openDepartureCalendar().submitAndSelectDepartureDate(departureDay)
                .submitAndSelectReturnDate(returnDay)
                .findTicket();

        assertTrue(flightsPage.checkFlightsExist());
    }

    //Bu testin sonucuna tarih ve lokasyon seçtiğimiz kısımdaki aktarma seçeneği butonu doğru çalışmamaktadır.
    @Test(priority = 10,description = "Checks if connected/non-connected flights button on the homepage working.")
    public void checkIfConnectedFlightsButtonWorking(){
        homePage.goTo(url)
                .closeCookie()
                .clickFrom().selectFrom(from)
                .clickTo().selectTo(to)
                .openDepartureCalendar().submitAndSelectDepartureDate(departureDay)
                .submitAndSelectReturnDate(returnDay)
                .chooseNonConnectedFlights()
                .findTicket();

        flightsPage.expandMore();

        assertFalse(flightsPage.checkConnectedFlightsExist());

    }

    @Test(priority = 11,description = "Checks if provider brands are shown in the filter section.")
    public void checkIfProvidersShowedInFilter(){

        homePage.goTo(url)
                .closeCookie()
                .clickFrom().selectFrom(from)
                .clickTo().selectTo(to)
                .openDepartureCalendar()
                .submitAndSelectDepartureDate(departureDay)
                .submitAndSelectReturnDate(returnDay)
                .findTicket();

        flightsPage.chooseDirectFlight(isDirect);

        assertTrue(flightsPage.checkProvidersExistInFilter());

    }

    @Test(priority = 12,description = "Verifies departure date which user entered in the home page.")
    public void verifyDepartureDate(){

        homePage.goTo(url)
                .closeCookie()
                .clickFrom().selectFrom(from)
                .clickTo().selectTo(to)
                .openDepartureCalendar()
                .submitAndSelectDepartureDate(departureDay)
                .submitAndSelectReturnDate(returnDay);

        String selectedDepDate = homePage.getDepartureDate();

        homePage.findTicket();

        assertEquals(flightsPage.getDepartureDate(),selectedDepDate);

    }

    @Test(priority = 13,description = "Verifies return date which user entered in the home page.")
    public void verifyReturnDate(){

        homePage.goTo(url)
                .closeCookie()
                .clickFrom().selectFrom(from)
                .clickTo().selectTo(to)
                .openDepartureCalendar()
                .submitAndSelectDepartureDate(departureDay)
                .submitAndSelectReturnDate(returnDay);

        String selectedReturnDate = homePage.getReturnDate();

        homePage.findTicket();

        assertEquals(flightsPage.getReturnDate(),selectedReturnDate);

    }

    @Test(priority = 14,description = "Verifies departure flight provider which user selected in the flights filter section.")
    public void verifyDepartureProvider(){

        homePage.goTo(url)
                .closeCookie()
                .clickFrom().selectFrom(from)
                .clickTo().selectTo(to)
                .openDepartureCalendar()
                .submitAndSelectDepartureDate(departureDay)
                .submitAndSelectReturnDate(returnDay)
                .findTicket();

        flightsPage.chooseDirectFlight(isDirect)
                .chooseProvider(provider)
                .chooseDepartureFlight()
                .chooseReturnFlight()
                .selectTicket();

        assertEquals(flightInfoPage.getDepartureProvider(),provider);

    }

    @Test(priority = 15,description = "Verifies return flight provider which user selected in the flights filter section.")
    public void verifyReturnProvider(){

        homePage.goTo(url)
                .closeCookie()
                .clickFrom().selectFrom(from)
                .clickTo().selectTo(to)
                .openDepartureCalendar()
                .submitAndSelectDepartureDate(departureDay)
                .submitAndSelectReturnDate(returnDay)
                .findTicket();

        flightsPage.chooseDirectFlight(isDirect)
                .chooseProvider(provider)
                .chooseDepartureFlight()
                .chooseReturnFlight()
                .selectTicket();

        assertEquals(flightInfoPage.getReturnProvider(),provider);
    }

}
