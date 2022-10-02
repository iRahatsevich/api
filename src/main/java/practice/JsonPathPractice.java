package practice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.groovy.util.Maps;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class JsonPathPractice {

    public String uri = "https://gorest.co.in/public-api/users";

    @Test
    public void getAllUsers(){
        Response response = RestAssured
                .given()
                .baseUri(uri)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .request("GET");
        System.out.println(response.asPrettyString());
    }


    @Test
    public void jPathTest(){

        Response response = RestAssured
                .given()
                .baseUri(uri)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .request("GET");

        JsonPath jPath = response.jsonPath();

        //this will print 1st elements of data obj name
        System.out.println(jPath.getString("data[0].name"));

        //to get the whole object -> list of maps
        List<Map<String, Object>> jObj = jPath.getList("data");
        System.out.println(jObj.get(0).get("name"));
    }
}
