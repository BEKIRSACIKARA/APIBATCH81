
import base_urls.AutomationexerciseBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Get04 extends AutomationexerciseBaseUrl {

    /*
        Given
            https://automationexercise.com/api/brandsList
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be "text/html; charset=utf-8"
        And
            Status Line should be HTTP/1.1 200 OK
        And
             Number of H&M brands must be equal to Polo(H&M marka sayısı Polo marka sayısına eşit olmalı)
*/


    @Test
    public void get04(){

        //set the url
        spec.pathParams("first","brandsList");


        //set the expected data

        //send the request and get the response
        Response response=given().spec(spec).when().get("/{first}");
        JsonPath json=response.jsonPath();
        json.prettyPrint();

        //do assertion
        assertEquals(200,response.getStatusCode());
        assertEquals("text/html; charset=utf-8",response.contentType());
        assertEquals("HTTP/1.1 200 OK",response.getStatusLine());

        List<String> polo=response.jsonPath().getList("brands.findAll{it.brand=='Polo'}");
        System.out.println("Polo=" + polo.size());//6

        List<String> hm= response.jsonPath().getList("brands.findAll{it.brand=='H&M'}");
        System.out.println("H&M=" + hm.size());//5

        assertNotEquals(polo,hm);

    }
}