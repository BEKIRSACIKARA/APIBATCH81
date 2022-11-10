package odevler;

import base_urls.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.ReqresPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class odev05ObjectMapperpojo extends ReqresBaseUrl {
    //4:  Pojo Class ile  Object Mapper kullanarak yapınız.
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
    public void test04ObjectMapperPojo(){
    //set the url
    spec.pathParams("first", "users","second",2);
    //set the expected data
    ReqresPojo expectedData = new ReqresPojo("neo",null);
    System.out.println("expectedData = " + expectedData);
    //send the request and get the response
    Response response =given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().patch("/{first}/{second}");
    response.prettyPrint();
    //do assertion
ReqresPojo actualData = ObjectMapperUtils.convertJsonTojava(response.asString(), ReqresPojo.class);
    System.out.println("actualData = " + actualData);
    assertEquals(200, response.getStatusCode());
    assertEquals(expectedData.getName(), actualData.getName());
}



}
