package delete_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiDeletePojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;

public class Delete02 extends DummyRestApiBaseUrl {
/*
     URL: https://dummy.restapiexample.com/api/v1/delete/2
     HTTP Request Method: DELETE Request
     Test Case: Type by using Gherkin Language
     Assert:     i) Status code is 200
                 ii) "status" is "success"
                 iii) "data" is "2"
                 iv) "message" is "Successfully! Record has been deleted"
       */
/*
Given
https://dummy.restapiexample.com/api/v1/delete/2
When
DELETE Request
Then
            AND
                 i) Status code is 200
           AND
                 ii) "status" is "success"
            AND
                 iii) "data" is "2"
            AND
                 iv) "message" is "Successfully! Record has been deleted"
 */@Test
public void Delete02(){
    spec.pathParams("first","delete","second",2);
    DummyRestApiDeletePojo expected = new DummyRestApiDeletePojo("success","2","Successfully! Record has been deleted");
    Response response= given().spec(spec).when().delete("/{first}/{second}");
    response.prettyPrint();
    DummyRestApiDeletePojo actual = ObjectMapperUtils.convertJsonTojava(response.asString(),DummyRestApiDeletePojo.class);





}



}
