package tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.home_page.HomePage;

public class BaseTest {

    String from = "istanbul";
    String to = "amsterdam";
    int departureDay = 5;
    int returnDay = 10;

    HomePage homePage;

    @BeforeClass
    public void setUp(){
        homePage = new HomePage();
    }

    @AfterClass
    public void tearDown(){

    }

}
