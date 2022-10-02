package practice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import domains.dummyApi.DataCl;
import domains.dummyApi.DummyApi;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Type;
import java.sql.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DummyApiPractice {

    private static String apiHost = "https://dummy.restapiexample.com/api";
    private static String apiVersion = "/v1/employees";

    @Before
    public void setUp() {
        RestAssured.baseURI = apiHost + apiVersion;
    }

    @Test
    public void getAllUsers() throws JsonProcessingException {

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .request("GET");

// ----- example with jsonPath
//        JsonPath jp = response.jsonPath();
//        System.out.println(jp.getString("message")); // output will be message

// ----- deserializing complex json w objectMapper
        String json = response.body().asString();

        ObjectMapper om = new ObjectMapper();
        DummyApi dummy = om.readValue(json, DummyApi.class);

        Assert.assertEquals( "Successfully! All records has been fetched." , dummy.getMessage());
        System.out.println(dummy);
        System.out.println(dummy.getStatus());
        System.out.println(dummy.getData().get(0));
        System.out.println(dummy.getMessage());
        System.out.println(dummy.getData().size());


        //this api may give error ocasionally bc of 'too many attempts' -> just run again
    }

}
