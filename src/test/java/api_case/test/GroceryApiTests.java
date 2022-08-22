package api_case.test;

import api_case.models.GroceryItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

//Grocery Api Testlerinin olduğu sınıf.

public class GroceryApiTests extends BaseTest{

    private GroceryItem groceryItem;
    private String body;
    private String itemName;

    public GroceryApiTests(){
        groceryItem = new GroceryItem(3,"banana",5,75);

        try {
            body = mapper.writeValueAsString(groceryItem);
        }
        catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }

    }

    @Test(priority = 0,description = "Checks posting grocery item successfully by status code.")
    public void checkIfItemPostedSuccessfully(){

        response = groceryApi.postGroceryItem(body);

        Assert.assertEquals(response.statusCode(),201);
    }

    @Test(priority = 1,description = "Checks posting grocery item successfully by item name.")
    public void verifyPostedItemName(){
        groceryItem.setName("orange");

        try {
            body = mapper.writeValueAsString(groceryItem);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }

        response = groceryApi.postGroceryItem(body);

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(groceryItem.getName(),jsonPath.getString("name"));

    }

    @Test(priority = 2,description = "Checks if there is an error when grocery item posted with empty body.")
    public void checkIfErrorsEmptyBodyPosted(){

        body = null;

        response = groceryApi.postGroceryItem(body);

        Assert.assertEquals(response.statusCode(),405);

    }

    @Test(priority = 3,description = "Checks return of the get grocery items request not null and has items.")
    public void checkIfResponseReturnsItems(){

        response = groceryApi.getGroceryItems();

        List<GroceryItem> groceryItemList = null;

        try {
            groceryItemList = mapper.readValue(response.getBody().asString(),new TypeReference<>(){});
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }

        Assert.assertNotNull(groceryItemList);

    }

    @Test(priority = 4,description = "Checks first item's name from the return of the request is 'apple'")
    public void checkIfItemNameFromListCorrect(){

        response = groceryApi.getGroceryItems();

        List<GroceryItem> groceryItemList = null;

        try {
            groceryItemList = mapper.readValue(response.getBody().asString(),new TypeReference<>(){});
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }

        Assert.assertEquals(groceryItemList.get(0).getName(),"apple");

    }

    @Test(priority = 5,description = "Checks if there is an error when trying to get non existing item")
    public void validateErrorWhenGettingNonExistingItem(){

        itemName = "dragonFruit";
        response = groceryApi.getGroceryItem(itemName);

        Assert.assertEquals(response.statusCode(), "404");

    }

    @Test(priority = 6,description = "Checks the item which name 'grapes' has the expected id.")
    public void checkIfItemNameCorrect(){

        itemName = "grapes";
        int expectedId = 2;

        response = groceryApi.getGroceryItem(itemName);
        GroceryItem itemFromResponse = null;

        try {
            itemFromResponse = mapper.readValue(response.getBody().asString(), GroceryItem.class);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }

        Assert.assertEquals(itemFromResponse.getId(),expectedId);

    }

    @Test(priority = 7,description = "Checks the response content type is json.")
    public void verifyContentType(){

        itemName = "apple";
        response = groceryApi.getGroceryItem(itemName);

        Assert.assertEquals(response.getContentType(),"application/json");

    }

}
