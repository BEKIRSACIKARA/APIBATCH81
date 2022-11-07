package get_request;

import base_urls.ReqresBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class Get05b extends ReqresBaseUrl {
    /*
       Given
         https://reqres.in/api/unknown/3
       When
           User send a GET request to the URL
       Then
           HTTP Status Code should be 200
       And
           Response content type is "application/json"
       And
           Response body should be like;(Soft Assertion)
       {
       "data": {
           "id": 3,
           "name": "true red",
           "year": 2002,
           "color": "#BF1932",
           "pantone_value": "19-1664"
       },
       "support": {
           "url": "https://reqres.in/#support-heading",
           "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
       }
}
     */
    @Test
    public void get05() {
        //  i)  Set the URL,
        spec.pathParams("first", "unknown", "second", 3);
        // ii) Set the expected Data (beklenen datanin olusturulmasi, Post, Put, Patch)
        // Bizden post, put ya da patch istenmedigi icin bu case de kullanmayacagiz.

        // iii) Type code to send request ( Talep gondermek icin kod yazimi)
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // iv) Do Assertion (dogrulama yapmak)
        SoftAssert softAssert = new SoftAssert();
        JsonPath jsonPath=response.jsonPath();

        softAssert.assertEquals(response.statusCode(),200);
        softAssert.assertEquals(response.contentType(),"application/json; charset=utf-8");
        softAssert.assertEquals(jsonPath.getInt("data.id"),3,"Id değeri hatali");
        softAssert.assertEquals(jsonPath.getString("data.name"),"true red","Name değeri hatali");
        softAssert.assertEquals(jsonPath.getInt("data.year"),2002,"year değeri hatali");
        softAssert.assertEquals(jsonPath.getString("data.color"),"#BF1932","color değeri hatali");
        softAssert.assertEquals(jsonPath.getString("data.pantone_value  "),"19-1664","pantone_value   değeri hatali");
        softAssert.assertEquals(jsonPath.getString("support.url"),"https://reqres.in/#support-heading","url değeri hatali");
        softAssert.assertEquals(jsonPath.getString("support.text"),"To keep ReqRes free, contributions towards server costs are appreciated!","text değeri hatali");


        softAssert.assertAll();






    }


}
