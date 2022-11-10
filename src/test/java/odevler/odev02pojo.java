package odevler;

import base_urls.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.ReqresPojo;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class odev02pojo extends ReqresBaseUrl {
    //2: Pojo Class ile
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
    public void odev02pojo() {
        //Set the url
        spec.pathParam("first", "users");
        //set the Expected Data
        ReqresPojo expectedData = new ReqresPojo("morpheus", "leader");
        System.out.println("expectedData = " + expectedData);
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();
//do asserttion
        ReqresPojo actualData=response.as(ReqresPojo.class);
        System.out.println("actualData = " + actualData);
        assertEquals(201, response.getStatusCode());
        assertEquals(expectedData.getName(), actualData.getName());
        assertEquals(expectedData.getJob(), actualData.getJob());



    }


}
