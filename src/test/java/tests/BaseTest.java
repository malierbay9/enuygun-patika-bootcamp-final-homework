package tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.FlightsPage;
import pages.home_page.HomePage;

public class BaseTest {

    String url = "https://www.enuygun.com/ucak-bileti/";
    String from = "istanbul";
    String to = "amsterdam";
    String provider = "Türk Hava Yolları";
    int departureDay = 5;
    int returnDay = 10;
    boolean isDirect = true;
    HomePage homePage;
    FlightsPage flightsPage;

    @BeforeClass
    public void setUp(){
        homePage = new HomePage();
        flightsPage = new FlightsPage();
    }

    @AfterClass
    public void tearDown(){

    }

}
