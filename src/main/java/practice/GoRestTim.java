package practice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class GoRestTim {

    private static final String apiHost = "https://gorest.co.in/public";
    private static final String apiVersion = "/v2";
    private static final String token = "50b5ddee619187e249096e328191b37a7e09f9bddc9690e17a22ebc73b32fe8a";

    @Test
    public void test() {
        RestAssured.baseURI = apiHost + apiVersion;

        //to send a request you have to open a response(is interface)

        //given when then (act arrange assert)
        Response getResponse =  RestAssured
                //act
                .given()
                //ContentType is about type of data you send to server
                .contentType(ContentType.JSON)
                //headers include authorization and accept data in key-value type
                .header("Authorization", token)
                //this is about data YOU receive as a client
                .accept(ContentType.JSON)
                //act
                .when()
                //.body("") ->you can have body depending on request you are sending
                .get("/users"); // can be .post() ; .delete() ....


        System.out.println(getResponse.asPrettyString());
        Assert.assertEquals(200, getResponse.getStatusCode());

    }

    @Test
    public void test2(){

        RestAssured.baseURI = apiHost + apiVersion;

        String postBody = "{\n" +
                "     \"name\": \"hello\",\n" +
                "    \"email\": \"hello@test.com\",\n" +
                "    \"gender\": \"female\",\n" +
                "    \"status\": \"active\"\n" +
                "}";

        //to send a request you have to open a response(is interface)

        //given when then (act arrange assert)
        Response postResponse = RestAssured
                //act
                .given()
                //ContentType is about type of data you send to server
                .contentType(ContentType.JSON)
                //headers include authorization and accept data in key-value type
                .header("Authorization", token)
                //this is about data YOU receive as a client
                .accept(ContentType.JSON)
                //act
                .body(postBody) //->you can have body depending on request you are sending
                .when()
                .post("/users"); // can be .post() ; .delete() ....


        System.out.println(postResponse.asPrettyString());
        Assert.assertEquals(401, postResponse.getStatusCode());


    }


}
