package practice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

public class BookingPractice {

    String url ="https://restful-booker.herokuapp.com/booking";


    @Test
    public void test1(){
        RestAssured.baseURI = url;
        String jsonStr = "{\"firstname\" : \"Jim\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(jsonStr)
                .when()
                .request("post");

        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asPrettyString());
    }
}
