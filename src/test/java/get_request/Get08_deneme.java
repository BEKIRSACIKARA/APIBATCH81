package get_request;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class Get08_deneme extends JsonplaceholderBaseUrl {
    /*
    Given
       https://jsonplaceholder.typicode.com/todos/4
   When
       I send GET Request to the URL
   Then
       Status code is 200
       And "completed" is true
       And "userId" is 1
       And "title" is "et porro tempora"
       And header "Via" is "1.1 vegur"
       And header "Server" is "cloudflare"
       {
           "userId": 1,
           "id": 4,
           "title": "et porro tempora",
           "completed": true
       }
*/
    @Test
    public void get08deneme() {
//Set the Url
        spec.pathParams("first", "todos", "second", 4);
//Set The Expected Data
        Map<String, Object> expectedData = new HashMap<String, Object>();
        expectedData.put("userId", 1);
        expectedData.put("id", 4);
        expectedData.put("title", "et porro tempora");
        expectedData.put("completed", true);
        System.out.println("expectedData = " + expectedData);
//Send The Request and Get The Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();
//Do Assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);
        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("id"), actualData.get("id"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));


    }


}
