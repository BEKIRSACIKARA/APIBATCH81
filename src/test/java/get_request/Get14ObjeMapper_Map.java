package get_request;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;
import utils.ObjectMapperUtils;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class Get14ObjeMapper_Map extends JsonplaceholderBaseUrl {
    /*
            Given
                https://jsonplaceholder.typicode.com/todos/198
            When
                 I send GET Request to the URL
             Then
                 Status code is 200
                 And response body is like {
                                            "userId": 10,
                                            "id": 198,
                                            "title": "quis eius est sint explicabo",
                                            "completed": true
                                          }
         */
    @Test
    public void get14Map() {
        //Set the Url
        spec.pathParams("first", "todos", "second", 198);
        //Set the expectedData
        String expectedDataInString = new JsonPlaceHolderTestData().expectedDataInString(10, "quis eius est sint explicabo", true);
        Map expectedData = ObjectMapperUtils.convertJsonTojava(expectedDataInString, Map.class);
        //System.out.println("expectedData = " + expectedData);
        //Set the request and Get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        // response.prettyPrint();
        //do asserttion
        Map actualData = ObjectMapperUtils.convertJsonTojava(response.asString(), Map.class);
        //  System.out.println("actualData = " + actualData);

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("userId"), actualData.get("userId"));


    }


}
