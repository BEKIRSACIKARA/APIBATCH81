package odevler;

import base_urls.ReqresBaseUrl;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.ReqresTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class odev03mapgson extends ReqresBaseUrl {
    //3: map gson ile
/*
        Given
            1) https://reqres.in/api/users/2
            2) {
                "name": "morpheus",
                "job": "zion president"
                }
        When
            I send Put Request to the Url
        Then
            Status code is 200
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "zion president",
                                                "updatedAt": "2022-10-02T11:35:05.693Z"
                                            }
*/
    @Test
    public void test3mapgson() {
        //Set the URL
        spec.pathParams("first", "users", "second", 2);
        //Set the Expected Data(put,post,patch)
        ReqresTestData obj = new ReqresTestData();
        Map<String, String> expectedData = obj.requestDataMetod("morpheus", "zion president");
        System.out.println("expectedData = " + expectedData);
        //Send the Request and Get the Response
        Response response= given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}/{second}");
        response.prettyPrint();
        //do asserttion
        Gson gson = new Gson();
        response.then().statusCode(201);
        Map<String, String> actualData = gson.fromJson(response.asString(), HashMap.class);
        System.out.println("actualData = " + actualData);
        assertEquals(expectedData.get("name"), actualData.get("name"));
        assertEquals(expectedData.get("job"), actualData.get("job"));

    }


}
