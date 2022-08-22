package api_case.test;


import api_case.api_listener.ApiTestListener;
import api_case.requests.GroceryApiRequests;
import api_case.requests.PetStoreApiRequests;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

// Testler çalışmadan önce (request sınıflarının instance edilmesi gibi) yapılması gereken işlemlerin yapıldığı sınıf.

@Listeners(ApiTestListener.class)   //Listener sınıfımızı entegre ediyoruz.
public abstract class BaseTest {

    GroceryApiRequests groceryApi;
    PetStoreApiRequests petStoreApiRequests;
    Response response;
    ObjectMapper mapper;

    @BeforeClass
    public void setUp(){
        mapper = new ObjectMapper();
        groceryApi = new GroceryApiRequests();
        petStoreApiRequests = new PetStoreApiRequests();
    }


}
