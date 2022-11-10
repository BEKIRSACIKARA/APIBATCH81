package odevler;

import base_urls.ReqresBaseUrl;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.ReqresPojo;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class odev03pojogson extends ReqresBaseUrl {
    //3: Pojo Class ile  Gson kullanarak yapınız.

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
    public void test03pojogson() {
        //SET THE URL
        spec.pathParams("first", "users", "second", 2);
        //set the expecteddata
        ReqresPojo expectedData = new ReqresPojo("morpheus", "zion president");
        System.out.println("expecteddata = " + expectedData);
        //send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().put("/{first}/{second}");
        System.out.println("response = " + response);
        //do asserttion
        Gson gson = new Gson();
        ReqresPojo actualData = gson.fromJson(response.asString(), ReqresPojo.class);
        System.out.println("actualData = " + actualData);
        response.then().statusCode(201);
        assertEquals(expectedData.getName(), actualData.getName());
        assertEquals(expectedData.getJob(), actualData.getJob());


    }


}
