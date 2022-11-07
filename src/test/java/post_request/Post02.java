package post_request;

import base_urls.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.RestfulTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class Post02 extends RestfulBaseUrl {
    /*
   Given
       1) https://restful-booker.herokuapp.com/booking
       2) {
            "firstname": "John",
            "lastname": "Doe",
            "totalprice": 11111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2021-09-09",
                "checkout": "2021-09-21"
             }
          }
   When
       I send POST Request to the Url
   Then
       Status code is 200
       And response body should be like {
                                           "bookingid": 5315,
                                           "booking": {
                                               "firstname": "John",
                                               "lastname": "Doe",
                                               "totalprice": 11111,
                                               "depositpaid": true,
                                               "bookingdates": {
                                                   "checkin": "2021-09-09",
                                                   "checkout": "2021-09-21"
                                               }
                                           }
                                        }
*/
    @Test
    public void testPost02() {
        //Set the Url
        spec.pathParams("first", "booking");
        //Set the Expected Data
        RestfulTestData obj = new RestfulTestData();
        Map<String, String> bookingdatesMap = obj.bookingdatesMethod("2021-09-09", "2021-09-21");
        Map<String, Object> expectedDataMap = obj.expectedDataMethod("John", "Doe", 11111, true, bookingdatesMap);

        System.out.println("expectedDataMap = " + expectedDataMap);
        Response response = given().spec(spec).contentType(ContentType.JSON).
                body(expectedDataMap).when().post("/{first}");
        //do asserttion
          Map<String,Object> actualData = response.as(HashMap.class); //de-Serialization
        System.out.println("actualData = " + actualData);
        assertEquals(expectedDataMap.get("firstname"), ((Map)actualData.get("booking")).get("firstname"));
        assertEquals(expectedDataMap.get("lastname"), ((Map)actualData.get("booking")).get("lastname"));
        assertEquals(expectedDataMap.get("totalprice"), ((Map)actualData.get("booking")).get("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"), ((Map)actualData.get("booking")).get("depositpaid"));
        assertEquals(bookingdatesMap.get("checkin"),((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(bookingdatesMap.get("checkout"),((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkout"));



    }


}
