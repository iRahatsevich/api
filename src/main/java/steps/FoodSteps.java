package steps;

import com.google.gson.Gson;
import cucumber.api.DataTable;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domains.AddNewFoodResponseBody;
import domains.CommitToDBResponse;
import domains.Food;
import domains.ResponseErrorMessageBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import utils.ConfigReader;

import utils.RestHTTPRequest;

import java.util.List;

import static utils.FoodDeliveryEndpoints.*;

public class FoodSteps {

    private Response response;
    Gson gson = new Gson(); // used for serializing and deserializing

    @Before
    public void setUp() {
        //clean cache so that tests are independent

//        String url = "http://" + ConfigReader.getPropertiesValue("food.delivery.host") + ":" +
//                ConfigReader.getPropertiesValue("food.delivery.port") + "/food/cache/add";

//         response = RestAssured
//                .given()
//                .baseUri(url)
//                .contentType(ContentType.JSON)
//                .accept(ContentType.JSON)
//                .when()
//                .request("POST");

        //same as :

        RestHTTPRequest.addHeaders();
        response = RestHTTPRequest.requestSpecification
                .when()
                .request("POST", CLEAR_CACHE.getEndpoint());

        Assert.assertEquals(200, response.getStatusCode());


    }

    @Given("^add new food to FoodDelivery with the following fields$")
    public void add_new_food_to_FoodDelivery_with_the_following_fields(List<Food> requestPayload) {
        System.out.println(requestPayload.get(0).getImageUrl());
        //serializing -> convert java to json string
        Food foodFromDataTable = requestPayload.get(0);

        String json = gson.toJson(foodFromDataTable);

        RestHTTPRequest.addHeaders(); //you can just use it in before hook and you dont need to call it again
        response = RestHTTPRequest.requestSpecification
                .body(json)
                .when()
                .request("POST" , FOOD_CACHE_ENDPOINT.getEndpoint());


    }

    @Then("^verify that status code is (\\d+)\\.$")
    public void verify_that_status_code_is(int expectedStatusCode) {
        Assert.assertEquals("food/cache/add endpoint status code mismatch", expectedStatusCode, response.getStatusCode());
    }

    @Then("^verify that food has been successfully added$")
    public void verify_that_food_has_been_successfully_added(List<Food> expectedResponsePayload) {
//how to validate response payload?
// deserialize json payload from response using GSON
        String responsePayload = response.getBody().asPrettyString();

        System.out.println(responsePayload);

        AddNewFoodResponseBody actualResponse = gson.fromJson(responsePayload, AddNewFoodResponseBody.class);

//verifying every single field that actual matches expected
        Assert.assertEquals(1, actualResponse.getFoodCached().size());

        Assert.assertEquals(expectedResponsePayload.get(0).getDescription(),
                actualResponse.getFoodCached().get(0).getDescription());

        Assert.assertEquals(expectedResponsePayload.get(0).getImageUrl(),
                actualResponse.getFoodCached().get(0).getImageUrl());

        Assert.assertEquals(expectedResponsePayload.get(0).getPrice(),
                actualResponse.getFoodCached().get(0).getPrice(), 0);

        Assert.assertEquals(expectedResponsePayload.get(0).getName(),
                actualResponse.getFoodCached().get(0).getName());

        Assert.assertEquals(expectedResponsePayload.get(0).getFoodType(),
                actualResponse.getFoodCached().get(0).getFoodType());


    }

    @Then("^verify response error message \"([^\"]*)\"$")
    public void verify_response_error_message(String expectedErrorMessage) {
        String actualJSON = response.getBody().asPrettyString();

        ResponseErrorMessageBody actualResponseObj = gson.fromJson(actualJSON, ResponseErrorMessageBody.class);

        Assert.assertEquals(expectedErrorMessage, actualResponseObj.getErrorMessage());
    }



    @When("^user commits the cache to DB$")
    public void user_commits_the_cache_to_DB()  {

    response = RestHTTPRequest.requestSpecification
               .when()
              .request("POST" , COMMIT_CACHE_TO_DB_ENDPOINT.getEndpoint()); // -> in order to import this value must have static import


    }

    @Then("^app should send the following response$")
    public void app_should_send_the_following_response(List<CommitToDBResponse> expectedResponse)  {

        String actualJson = response.getBody().asPrettyString();
        CommitToDBResponse actualPayload = gson.fromJson(actualJson , CommitToDBResponse.class);

        Assert.assertEquals("Commit to DB invalid number of food saved" , expectedResponse.get(0).getNumberOfFoodSaved() , actualPayload.getNumberOfFoodSaved());

        Assert.assertEquals("Commit to DB invalid message " , expectedResponse.get(0).getMessage() , actualPayload.getMessage());

        System.out.println(response.getBody().asPrettyString());
    }


}








