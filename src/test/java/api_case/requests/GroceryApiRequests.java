package api_case.requests;

import api_case.endpoints.GroceryEndPoints;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class GroceryApiRequests {

    Response response;

    public GroceryApiRequests(){
        baseURI = "http://localhost:3001";
    }


    public Response postGroceryItem(String body){

        response = given().header("Content-Type","application/json")
                .body(body)
                .when()
                .post(baseURI+GroceryEndPoints.ADD)
                .then()
                .extract().response();

        return response;

    }

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
