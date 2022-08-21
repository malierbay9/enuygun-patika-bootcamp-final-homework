package api_case.test;

import api_case.models.pet.Pet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import io.restassured.internal.http.HttpResponseException;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class PetStoreApiTests extends BaseTest {

    private long petId;
    private String status;
    private String petName;
    private String apiKey;

    public PetStoreApiTests() {

        petId = 51;
        apiKey = "laboris qui quis in";

    }

    @Test(priority = 0,description = "Checks return of the get pets by status request not null and has items.")
    public void checkIfResponseRetunsPets(){
        status = "available";
        response = petStoreApiRequests.getPetFindByStatus(status);

        List<Pet> petList = null;

        try {

            petList = mapper.readValue(response.getBody().asString(), new TypeReference<>() {});

        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }

        Assert.assertNotNull(petList);

    }

    @Test(priority = 1,description = "Checks if there is an error when requesting with wrong status input.")
    public void checkIfResponseStatus400WhenInputIsWrong(){
        status = "dead";
        response = petStoreApiRequests.getPetFindByStatus(status);

        try {

            List<Pet> petList = mapper.readValue(response.getBody().asString(), new TypeReference<>() {});

        }
        catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }

        Assert.assertEquals(response.statusCode(), 400);

    }

    @Test(priority = 2,description = "Checks if pet's 'status' from response same with 'status' input entered in the request.")
    public void checkIfPetStatusCorrect(){
        status = "pending";

        response = petStoreApiRequests.getPetFindByStatus(status);

        List<Pet> petList = null;
        try {

            petList = mapper.readValue(response.getBody().asString(), new TypeReference<>() {});

        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }

        Assert.assertEquals(petList.get(0).getStatus(), status);
    }

    @Test(priority = 3,description = "Checks third pet's name of the pet list which is required from get by status request has the same name with pet requested by same id")
    public void checkIfPetNameIsCorrect(){
        status = "available";
        response = petStoreApiRequests.getPetFindByStatus(status);

        List<Pet> petList = null;
        Pet petFromResponse = null;

        try {

            petList = mapper.readValue(response.getBody().asString(), new TypeReference<>() {});
            petId = petList.get(2).getId();

            response = petStoreApiRequests.getPetById(petId);
            petFromResponse = mapper.readValue(response.getBody().asString(), Pet.class);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(petList.get(2).getName(), petFromResponse.getName());

    }

    @Test(priority = 4,description = "Checks updating pet successfully.")
    public void checkIfPetUpdatedSuccessfully(){

        petName = "cicikus";
        status = "available";

        try {

            petStoreApiRequests.postPet(petId, petName, status);

        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }

        petName = "karabas";
        status = "pending";

        response = petStoreApiRequests.postUpdatePet(petId, petName, status);

        JsonPath jsonPath = response.jsonPath();

        Assert.assertNotNull(response.getBody());
        Assert.assertEquals(jsonPath.getString("code"), "200");
        Assert.assertEquals(jsonPath.getString("message"), String.valueOf(petId));
    }

    @Test(priority = 5,description = "Checks successfull deleting.")
    public void verifySuccessfullDelete() {

        response = petStoreApiRequests.deletePetById(petId, apiKey);//trycatch

        JsonPath jsonPath = response.jsonPath();

        Assert.assertNotNull(response.getBody());
        Assert.assertEquals(jsonPath.getString("code"), "200");
        Assert.assertEquals(jsonPath.getString("message"), String.valueOf(petId));

    }

    @Test(priority = 6,description = "Checks if there is an error when user tries to delete non-existing pet.")
    public void checkIfErrorsWhenDeletingNonExistingPet() {

        Assert.expectThrows(HttpResponseException.class
                ,()->{response = petStoreApiRequests.deletePetById(petId, apiKey);});

    }

    @Test(priority = 7,description = "Checks if there is an error when user tries to get non-existing pet by id.")
    public void checkIfErrorsWhenGettingNonExistingPet(){

        Assert.expectThrows(HttpResponseException.class
                ,()->{response = petStoreApiRequests.getPetById(petId);});

    }


}
