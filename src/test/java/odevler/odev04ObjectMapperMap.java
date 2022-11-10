package odevler;

import base_urls.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import test_data.ReqresTestData;

import java.io.IOException;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class odev04ObjectMapperMap extends ReqresBaseUrl {
    //4: Map ile  Object Mapper kullanarak yapınız.
  /*
       Given
           1) https://reqres.in/api/users/2
           2) {
                "name": "neo"
               }
       When
            I send PATCH Request to the Url
       Then
             Status code is 200
             And response body is like   {
                                                "name": "neo",
                                                "updatedAt": "2022-10-02T12:53:21.675Z"
                                         }
    */
    @Test
    public void test04ObjectMapperMap() throws IOException {
        //set the url
        spec.pathParams("first", "users", "second", 2);
        //set the expecteddata
        ReqresTestData obj = new ReqresTestData();
        String jsonString = obj.expectedDataInString("neo");
        System.out.println("jsonString = " + jsonString);
        HashMap expectedData = new ObjectMapper().readValue(jsonString, HashMap.class);
        System.out.println("expectedData = " + expectedData);
        //send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().patch("/{first}/{second}");
        response.prettyPrint();

        //do asserttion
        HashMap actualData = new ObjectMapper().readValue(response.asString(), HashMap.class);
        System.out.println("actualData = " + actualData);
        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.get("name"), actualData.get("name"));

    }


}
