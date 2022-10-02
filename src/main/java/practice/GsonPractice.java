package practice;

import com.google.gson.Gson;
import domains.ListUsersResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class GsonPractice {

    @Test
    public void test2() {

        //deserealization
        String url = "https://gorest.co.in/public-api/users";

        Response response = RestAssured
                .given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .request("GET");

        Assert.assertEquals(200, response.getStatusCode());
String jsonString = response.getBody().toString();
        Gson gson = new Gson();
                                           //fromJson (String Json ,                 which class to convert to)
        ListUsersResponse responseBody = gson.fromJson(jsonString, ListUsersResponse.class);
      //  System.out.println(response.asPrettyString());

        System.out.println(responseBody.getUsers().get(0));
    }
}