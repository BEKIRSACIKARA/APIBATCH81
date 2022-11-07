package delete_requests;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import utils.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.*;


public class Delete01 extends JsonplaceholderBaseUrl {
    /*
        Given
            https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 200
		 	And Response body is { }
     */
@Test
    public void delete01() {
    //Set   the Url
    spec.pathParams("first", "todos", "second",198);
    //Set the expectedData
    Map<String,String>expectedData=new HashMap<>();
    //Set   the request and get the response
    Response response=given().spec(spec).when().delete("/{first}/{second}");
    response.prettyPrint();
   //do asserttion
    Map actualData=ObjectMapperUtils.convertJsonTojava(response.asString(), HashMap.class);

    assertEquals(200, response.getStatusCode());

    assertEquals(expectedData,actualData);

    assertTrue(actualData.isEmpty());

}




}
