package get_request;

import base_urls.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class Get15 extends RestfulBaseUrl {
    /*  Given
	            https://restful-booker.herokuapp.com/booking/22
        When
		 		I send GET Request to the URL
		Then
		 		Status code is 200
           {
                   "firstname": "Guoqiang",
                   "lastname": "Morante Briones",
                   "totalprice": 111,
                   "depositpaid": true,
                   "bookingdates": {
                       "checkin": "2018-01-01",
                       "checkout": "2019-01-01"
                   },
                   "additionalneeds": "Breakfast"
            }  */
    @Test
    public void get15() {
        //Set the Url
        spec.pathParams("first", "booking", "second", 22);
        //set the expectedData
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01", "2019-01-01");
        BookingPojo expectedData = new BookingPojo("Guoqiang", "Morante Briones", 111, true, bookingDatesPojo, "Breakfast");
        //send   the    request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
//do asserttion
//hard assertion
       BookingPojo actualData = ObjectMapperUtils.convertJsonTojava(response.asString(), BookingPojo.class);
       assertEquals(200, response.getStatusCode());
       assertEquals(expectedData.getFirstname(), actualData.getFirstname());
       assertEquals(expectedData.getLastname(), actualData.getLastname());
       assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
       assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());
       assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());
       assertEquals(bookingDatesPojo.getCheckin(),actualData.getBookingdates().getCheckin());
       assertEquals(bookingDatesPojo.getCheckout(),actualData.getBookingdates().getCheckout());
// soft assertion
 //1.adım soft assert objesi oluştur.
        SoftAssert softAssert= new SoftAssert();
        //2. adım assertion yao
        softAssert.assertEquals(actualData.getFirstname(),expectedData.getFirstname(),"Firstname uyuşmadı");
        softAssert.assertEquals(actualData.getLastname(),expectedData.getLastname(),"Lastname( uyuşmadı");
        softAssert.assertEquals(actualData.getTotalprice(),expectedData.getTotalprice(),"Totalprice uyuşmadı");
        softAssert.assertEquals(actualData.getDepositpaid(),expectedData.getDepositpaid(),"Depositpaid uyuşmadı");
        softAssert.assertEquals(actualData.getAdditionalneeds(),expectedData.getAdditionalneeds(),"Additionalneeds uyuşmadı");

        softAssert.assertEquals(actualData.getBookingdates().getCheckin(),expectedData.getBookingdates().getCheckin(),"Checkin uyuşmadı");
        softAssert.assertEquals(actualData.getBookingdates().getCheckout(),expectedData.getBookingdates().getCheckout(),"Checkout uyuşmadı");
        //3 asımassertal metodunu kullan
        softAssert.assertAll();






    }


}
