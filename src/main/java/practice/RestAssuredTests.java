package practice;

import com.google.gson.Gson;
import domains.ListUsersResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;

public class RestAssuredTests {


    @Test
    public void test1() {


        String url = "https://gorest.co.in/public-api/users";
        String body = "{\n" +
                "    \"name\": \"irynaR\",\n" +
                "    \"email\": \"new@new.com\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"status\" :\"Active\"\n" +
                "}";

        String listUsersURL = "https://gorest.co.in/public-api/users";

        //RestAssured library is one of many Java http clients(client -> is who sends a request)
        //RestAssured uses BDD syntax :
        //given -> headers
        //when -> endpoints . http method type (GET , POST ) , body
        // then -> validate the response code and body
        Response response = RestAssured
                .given()
                .baseUri(listUsersURL)
                .contentType(ContentType.JSON) // accept request body in JSON format
                .accept(ContentType.JSON) // accept response body in JSON format
                .when()
                .request("GET");



        Assert.assertEquals(200, response.getStatusCode());
        System.out.println(response.getBody().asPrettyString());

    }


    @Test
    public void test2() {
        String url = "https://gorest.co.in/public-api/users";

        Response response = RestAssured
                .given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .request("GET");

        Assert.assertEquals(200, response.getStatusCode());

        Gson gson = new Gson();
                                           //fromJson (String Json ,                 which class to convert to)
        ListUsersResponse responseBody = gson.fromJson(response.getBody().asString(), ListUsersResponse.class);


        System.out.println(responseBody.getUsers());
//        System.out.println(response.getBody().asPrettyString());
//        System.out.println(response.getBody().asPrettyString().contains("irynaR"));
    }


    @Test
    public void sendFileInBody(){
        String url = "https://gorest.co.in/public-api";

        Response response = RestAssured
                .given()
                .baseUri(url)
                .multiPart("file" , new File("C:\\Users\\irene\\OneDrive\\Documents\\TC_ID.docx"))
                .when()
                .post("/users");

        System.out.println(response.asPrettyString());



    }

}











