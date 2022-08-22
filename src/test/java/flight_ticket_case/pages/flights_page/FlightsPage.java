package flight_ticket_case.pages.flights_page;


import flight_ticket_case.pages.BasePage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static flight_ticket_case.pages.flights_page.FilterSection.*;
import static flight_ticket_case.pages.flights_page.ResultsSection.*;

//Uçuşların olduğu sayfanın page object sınıfıdır.Testlerde kullanılacak metotları içerir.
//Locatorları FilterSection ve ResultsSection sınıflarının static olarak import edilmesiyle kullanır.

public class FlightsPage extends BasePage {

    public FlightsPage chooseDirectFlight(boolean isDirect) {

        waitElementInvisible(disabledFilter);   //filtreye erişmek için bu elementin yok olmasını bekleriz

        //isDirect in değerine göre aktarmalı ya da aktarmasız uçuşları filtreler
        if (isDirect) {
            moveToElement(directFlightsLabel);
            click(onlyDirectFlights);
        }
        else {
            click(directFlightsLabel);
        }

        click(closeDirectOptions);

        return this;
    }

    public FlightsPage chooseProvider(String provider) {
        click(showAirlines);
        click(clearSelectedProviders);

        waitElementPresent(showAllProviders);
        click(showAllProviders);


        //filtrede bulunun havayollarıyla provider değerini kıyaslar. Eşit olunca havayolunu seçer.
        findElements(providersInFilter).stream().forEach(prvdr -> {
            if (prvdr.getText().equals(provider)) {
                prvdr.click();
            }
        });

        return this;
    }

    //Görüntülenmeyen diğer uçuşların hepsinin görüntülenmesini sağlar.
    //Sayfada 'Aynı Fiyatlı Diğer Uçuşlar' butonu olduğu sürece ona tıklar.
    public FlightsPage expandMore() {
        int i = 0;

        try {
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
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        return this;
    }

    public FlightsPage chooseDepartureFlight() {

        waitElementsVisible(departureFlights);
        clickFromList(departureFlights, 0);

        return this;
    }

    public FlightsPage chooseReturnFlight() {

        waitElementInvisible(overlay);
        waitElementsVisible(returnFlights);
        clickFromList(returnFlights, 0);

        return this;
    }

    public void selectTicket() {

        waitElementPresent(returnFlightRadioBtn);   //dönüş uçuşunun seçili olmasını bekler
        click(chooseTicketBtn);

    }

    public boolean checkFlightsExist(){
        return isDisplayed(fligtPairs);
    }

    //Aktarmalı uçuş var mı diye kontrol eder.
    public boolean checkConnectedFlightsExist(){
        return isDisplayed(connectedFlightInfo);
    }

    public boolean checkProvidersExistInFilter(){
        moveToElement(showAirlines);
        click(showAirlines);
        return isDisplayed(providersInFilter);
    }

    public String getDepartureDate(){
        return getElementText(chosenDepartureDate);
    }

    public String getReturnDate(){
        return getElementText(chosenReturnDate);
    }

}
