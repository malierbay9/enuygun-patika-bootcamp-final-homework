package flight_ticket_case.tests;


import flight_ticket_case.pages.home_page.HomePage;
import flight_ticket_case.util.DriverFactory;
import flight_ticket_case.util.TestListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import flight_ticket_case.pages.FlightInfoPage;
import flight_ticket_case.pages.flights_page.FlightsPage;

// Testler çalışmadan önce (page objectlerinin instance edilmesi gibi) ve testler bittikten sonra yapılması gereken işlemlerin yapıldığı sınıf.

@Listeners(TestListener.class)      //Listener sınıfımızı entegre ediyoruz.
public abstract class BaseTest {


    String url = "https://www.enuygun.com/ucak-bileti/";
    String from = "istanbul";
    String to = "amsterdam";
    String provider = "Türk Hava Yolları";
    int departureDay = 5;
    int returnDay = 10;
    boolean isDirect = true;        //gerekli parametreler

    HomePage homePage;
    FlightsPage flightsPage;
    FlightInfoPage flightInfoPage;

    @BeforeClass
    public void setUp(){
        homePage = new HomePage();
        flightsPage = new FlightsPage();
        flightInfoPage = new FlightInfoPage();
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
