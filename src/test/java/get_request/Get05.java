package get_request;

import base_url.JsonplaceholderBaseUrl;
import base_url.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.Request;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get05 extends RestfulBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking
    When
        User sends get request to the URL
    Then
        Status code is 200
And
   Among the data there should be someone whose firstname is "Johhny" and lastname is "Dear"
 */
    @Test
    public void get01() {
        // Set The URL
        spec.pathParam("first", "booking").queryParams("firstname", "Ali", "lastname", "Cengiz");
        // Expected Data

        // Send the request and Get Response
        Response response = given().when().spec(spec).get("/{first}");
        response.prettyPrint();
        //do asserttion(1.yol (hard Assert))
        assertEquals(200, response.getStatusCode());
        assertTrue(response.asString().contains("bookingid"));

    }
}
