package api_case.requests;

import api_case.endpoints.PetStoreEndPoints;
import api_case.models.pet.Category;
import api_case.models.pet.Pet;
import api_case.models.pet.Tag;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ArrayList;

import static io.restassured.RestAssured.*;


public class PetStoreApiRequests {

    Response response;

    public PetStoreApiRequests(){
        baseURI = "https://petstore.swagger.io/v2";
    }


    public Response getPetFindByStatus(String status){

        response = given()
                .header("Content-Type","application/json")
                .param("status",status)
                .when()
                .get(baseURI+ PetStoreEndPoints.FIND_BY_STATUS)
                .then()
                .extract().response();

        return response;
    }


    public Response getPetById(long id){

        response = given()
                .header("Content-Type","application/json")
                .pathParam("petId",id)
                .when()
                .get(baseURI+PetStoreEndPoints.PET_BY_ID)
                .then()
                .statusCode(200)
                .extract().response();

        return response;

    }

    public void postPet(long id,String name,String status) throws JsonProcessingException {
        Pet pet = new Pet(id,new Category(),name,new ArrayList<String>(),new ArrayList<Tag>(),status);

        String body = new ObjectMapper().writeValueAsString(pet);

        given()
                .header("Content-Type","application/json")
                .body(body).
        when()
                .post(baseURI+PetStoreEndPoints.ADD_PET).
        then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();

    }


    public Response postUpdatePet(long id,String name,String status){

        response = given()
                .header("Content-Type","application/x-www-form-urlencoded; charset=utf-8")
                .pathParam("petId",id)
                .formParam("name",name)
                .formParam("status",status)
                .when()
                .post(baseURI+PetStoreEndPoints.UPDATE_PET)
                .then()
                .statusCode(200)
                .extract().response();

        return response;
    }


    public Response deletePetById(long id,String apiKey){

        response = given()
                .header("Content-Type","application/json")
                .header("api-key",apiKey)
                .pathParam("petId",id)
                .when()
                .delete(baseURI+PetStoreEndPoints.DELETE_PET)
                .then()
                .statusCode(200)
                .extract().response();

        return response;
    }



}
