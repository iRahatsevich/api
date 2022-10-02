package practice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domains.UserGoRest;
import domains.reqresUsers.UserReqres;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class ObjectMapperPractice {

    @Test
    public void deserializationMapper() throws JsonProcessingException {

        ObjectMapper om = new ObjectMapper();
          // json string
        String jsonUser = "{\n" +
                "    \"name\": \"irynaR\",\n" +
                "    \"email\": \"new@new.com\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"status\" :\"Active\"\n" +
                "}";
//      class in pojo                        json string    class deser. to
        UserGoRest userGoRest = om.readValue(jsonUser , UserGoRest.class);

        System.out.println(userGoRest.getName());
        System.out.println(userGoRest.getEmail());
        System.out.println(userGoRest.getGender());
        System.out.println(userGoRest.getStatus());

//in order to be able to print obj as String -> make sure to
// add toString method in pojo
        System.out.println(userGoRest.toString());

    }


    @Test
    public void serializationMapper() throws IOException {

        ObjectMapper om = new ObjectMapper();
        UserGoRest userObj = new UserGoRest("Tom" , "tom@test.com", "Male" , "Active");

        //----write object to json
        String json = om.writeValueAsString(userObj);
        System.out.println(json);

        //----- write obj->json pretty print
        String j2 = om.writerWithDefaultPrettyPrinter().writeValueAsString(userObj);
        System.out.println(j2);

        //---- to write to json file     name for new file    name of obj
        om.writeValue(new File("objMapData.json") , userObj);
    }

    @Test
    public void test3() throws JsonProcessingException {

        String jsonUser = "{\n" +
                "    \"data\": {\n" +
                "        \"id\": 1,\n" +
                "        \"email\": \"george.bluth@reqres.in\",\n" +
                "        \"first_name\": \"George\",\n" +
                "        \"last_name\": \"Bluth\",\n" +
                "        \"avatar\": \"https://reqres.in/img/faces/1-image.jpg\"\n" +
                "    },\n" +
                "    \"support\": {\n" +
                "        \"url\": \"https://reqres.in/#support-heading\",\n" +
                "        \"text\": \"To keep ReqRes free, contributions towards server costs are appreciated!\"\n" +
                "    }\n" +
                "}";


        ObjectMapper om = new ObjectMapper();
        UserReqres user = om.readValue(jsonUser , UserReqres.class);
        System.out.println(user);


    }

}






