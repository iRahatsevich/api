package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RestHTTPRequest {

    public static RequestSpecification requestSpecification = RestAssured.given();

    public static void addHeaders(){
        String baseURI = "http://" + ConfigReader.getPropertiesValue("food.delivery.host") + ":" +
                ConfigReader.getPropertiesValue("food.delivery.port");

        requestSpecification
                .baseUri(baseURI)
                .contentType(ContentType.JSON)
                .contentType(ContentType.JSON);

    }
}
