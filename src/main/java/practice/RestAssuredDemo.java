package practice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestAssuredDemo {
    public static void main(String[] args) {


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
                .request("get");


        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asPrettyString());

    }
}
