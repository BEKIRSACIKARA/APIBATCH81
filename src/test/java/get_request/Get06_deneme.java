package get_request;

import base_urls.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.*;
import static org.hamcrest.Matchers.*;


public class Get06_deneme extends RestfulBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/257
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is "application/json"
        And
            Response body should be like;
         {
    "firstname": "Bradley",
    "lastname": "Pearson",
    "totalprice": 132,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2022-10-27",
        "checkout": "2022-11-07"
    },
    "additionalneeds": "None"
}
     */
    @Test
    public void get01() {
        // Set The URL
        spec.pathParams("first", "booking", "second", "257");
        // Expected Data
        //send the request and get the response (talep gonder ve cevap al)
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();
// 4. Do Assertion
      /*  Then
        HTTP Status Code should be 200
        And
        Response content type is "application/json"
        And
        Response body should be like;
        {
            "firstname": "Bradley",
                "lastname": "Pearson",
                "totalprice": 132,
                "depositpaid": false,
                "bookingdates": {
            "checkin": "2022-10-27",
                    "checkout": "2022-11-07"
        },
            "additionalneeds": "None"
        }
     */
        response.then().assertThat().statusCode(200).
                contentType(ContentType.JSON).
                body("firstname", equalTo("Berk"),
                        "lastname", equalTo("Kara"),
                        "totalprice", equalTo(1988),
                        "depositpaid", equalTo(true),
                        "bookingdates.checkin", equalTo("2016-08-30"),
                        "bookingdates.checkout", equalTo("2022-10-28"),
                        "additionalneeds", equalTo("Breakfast"));
//2.yol
        JsonPath json = response.jsonPath();
        assertEquals("Berk", json.getString("firstname"));
        assertEquals("Kara", json.getString("lastname"));
        assertEquals(1988, json.getInt("totalprice"));
        assertTrue(json.getBoolean("depositpaid"));
        assertEquals("2016-08-30", json.getString("bookingdates.checkin"));
        assertEquals("2022-10-28", json.getString("bookingdates.checkout"));
//3. yol
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(json.getString("firstname"), "Berk", "Firstname hatalı***");
        softAssert.assertEquals(json.getString("lastname   "), "Kara", "Lastname hatalı***");
        softAssert.assertEquals(json.getInt("totalprice"), 1988, "totalprice hatalı***");
        softAssert.assertTrue(json.getBoolean("depositpaid"), "depositpaid hatalı***");
        softAssert.assertEquals(json.getString("bookingdates.checkin"), "2016-08-30", "bookingdates.checkin hatalı***");
        softAssert.assertEquals(json.getString("bookingdates.checkout"), "2022-10-28", "bookingdates.checkout hatalı***");
        softAssert.assertAll();


    }


}
