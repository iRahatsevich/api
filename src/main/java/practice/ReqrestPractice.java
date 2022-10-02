package practice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domains.reqresUsers.UserReqres;
import org.junit.Test;

public class ReqrestPractice {

    @Test
    public void deserializationObjMap() throws JsonProcessingException {

        ObjectMapper om = new ObjectMapper();
        String json ="{\n" +
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

        UserReqres userObj = om.readValue(json , UserReqres.class);
        System.out.println(userObj);
        System.out.println(userObj.getData());
        System.out.println(userObj.getSupport());
    }
}
