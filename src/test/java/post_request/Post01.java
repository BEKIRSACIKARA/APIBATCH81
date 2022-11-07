package post_request;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class Post01 extends JsonplaceholderBaseUrl {
    /*
       Given
         1) https://jsonplaceholder.typicode.com/todos
         2)
      When
          I send POST Request to the Url
      Then
          Status code is 201 {
               "userId": 55,
               "title": "Tidy your room",
               "completed": false
                            }
      And
          response body is like {
                                  "C": 55,
                                  "title": "Tidy your room",
                                  "completed": false,
                                  "id": 201
                                  }
   */
    @Test
    public void post01() {
        //1. Set The URL
        spec.pathParam("first", "todos");

// 2. Set The Expected Data ( put, post, patch)
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", 55);
        expectedData.put("title", "Tidy your room");
        expectedData.put("completed", false);


        // 3. Send The Request And Get The Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        // 4. Do Assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
    }


}
