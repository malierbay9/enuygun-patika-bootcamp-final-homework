package api_case.requests;

import api_case.endpoints.GroceryEndPoints;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

//Grocery Requestlerinin oluşturan ve onların responselarını döndüren metotları içerir.

public class GroceryApiRequests {

    Response response;

    public GroceryApiRequests(){
        baseURI = "http://localhost:3001";  //Mockoon kullanılarak mockservice oluşturuldu. Bu servise ait URI.
    }

    //  POST/add
    public Response postGroceryItem(String body){

        response = given().header("Content-Type","application/json")
                .body(body)
                .when()
                .post(baseURI+GroceryEndPoints.ADD)
                .then()
                .extract().response();

        return response;

    }

    // GET/allGrocery
    public Response getGroceryItems(){

        response = given()
                .header("Content-Type","application/json")
                .when()
                .get(baseURI+GroceryEndPoints.ALL_GROCERY)
                .then()
                .statusCode(200)
                .extract().response();

        return response;

    }

    //  GET//allGrocery/{name}
    public Response getGroceryItem(String name){

        response = given()
                .header("Content-Type","application/json")
                .pathParam("name",name)
                .when()
                .get(baseURI+ GroceryEndPoints.GROCERY_FROM_NAME)
                .then()
                .extract().response();

        return response;

    }

}
