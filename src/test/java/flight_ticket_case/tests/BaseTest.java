package flight_ticket_case.tests;


import flight_ticket_case.pages.home_page.HomePage;
import flight_ticket_case.util.DriverFactory;
import flight_ticket_case.util.TestListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import flight_ticket_case.pages.ReservationPage;
import flight_ticket_case.pages.flights_page.FlightsPage;

@Listeners(TestListener.class)
public abstract class BaseTest {


    String url = "https://www.enuygun.com/ucak-bileti/";
    String from = "istanbul";
    String to = "amsterdam";
    String provider = "Türk Hava Yolları";
    int departureDay = 5;
    int returnDay = 10;
    boolean isDirect = true;
    HomePage homePage;
    FlightsPage flightsPage;
    ReservationPage reservationPage;

    @BeforeClass
    public void setUp(){
        homePage = new HomePage();
        flightsPage = new FlightsPage();
        reservationPage = new ReservationPage();
    }

    @AfterClass
    public void tearDown(){
        try {
            DriverFactory.quitDriver();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

}
