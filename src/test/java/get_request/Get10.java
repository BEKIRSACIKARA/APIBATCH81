package get_request;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get10 extends GoRestBaseUrl {
    /*
       Given
           https://gorest.co.in/public/v1/users/2986
       When
           User send GET Request to the URL
       Then
           Status Code should be 200
       And
           Response body should be like{
       "meta": null,
       "data": {
                    "id": 2986,
                    "name": "Navin Talwar",
                    "email": "navin_talwar@mclaughlin.name",
                    "gender": "male",
                    "status": "inactive"
                    }
    */
    @Test
    public void get10() {
        spec.pathParams("first", "users", "second", 2986);

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id", 2986);
        dataMap.put("name", "Navin Talwar");
        dataMap.put("email", "navin_talwar@mclaughlin.name");
        dataMap.put("gender", "male");
        dataMap.put("status", "inactive");

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("meta", null);
        expectedData.put("data", dataMap);
        System.out.println(expectedData);
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        Map<String, Object> actualData = response.as(HashMap.class);
        assertEquals(dataMap.get("id"), ((Map) (actualData.get("data"))).get("id"));
        assertEquals(dataMap.get("name"), ((Map) (actualData.get("data"))).get("name"));
        assertEquals(dataMap.get("email"), ((Map) (actualData.get("data"))).get("email"));
        assertEquals(dataMap.get("gender"), ((Map) (actualData.get("data"))).get("gender"));
        assertEquals(dataMap.get("status"), ((Map) (actualData.get("data"))).get("status"));
        assertEquals(expectedData.get("meta"), actualData.get("meta"));
        System.out.println(actualData);

    }


}
