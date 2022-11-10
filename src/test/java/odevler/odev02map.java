package odevler;

import base_urls.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.ReqresTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;


public class odev02map extends ReqresBaseUrl {
    //2:  Map ile
/*
        Given
            1) https://reqres.in/api/users
            2) {
                "name": "morpheus",
                "job": "leader"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "leader",
                                                "id": "496",
                                                "createdAt": "2022-10-04T15:18:56.372Z"
                                              }
*/

    @Test
    public void odev02map() {
        //Set the URL
        spec.pathParam("first", "users");
        //Set the Expected Data(put,post,patch)
        ReqresTestData obj = new ReqresTestData();
        Map<String, String> expectedData = obj.requestDataMetod("morpheus", "leader");
        //Send the Request and Get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();
        //do asserttion
        Map<String, String> actualData = response.as(HashMap.class);

        System.out.println("actualData = " + actualData);
        assertEquals(201, response.getStatusCode());
        assertEquals(expectedData.get("name"), actualData.get("name"));
        assertEquals(expectedData.get("job"), actualData.get("job"));

    }


}
