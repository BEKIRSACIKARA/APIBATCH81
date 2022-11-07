package get_request;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class Get07_deneme extends JsonplaceholderBaseUrl {
    /*
        Given
              https://jsonplaceholder.typicode.com/todos
      When
          I send GET Request to the URL == > URL'e Get Request gonderin
      Then
          1)Status code is 200 == > Status kodu 200 olmali
          2)Print all ids greater than 190 on the console ==> id si 190 dan buyuk olanlari konsola yazdirin
            Assert that there are 10 ids greater than 190 == > 10 tane id nin 190 dan buyuk oldugunu dogrulayin
          3)Print all userIds whose ids are less than 5 on the console ==> id si 5 den kucuk olan tum userid lerini konsolunu yazdirin
            Assert that the number of userIds whose ids are less than 5 is 4 ==> id si 5 den kucuk olan 4 tane userId oldugunu dogrulayin
          4)Print all titles whose ids are less than 5 ==> ıd si 5 den kucuk olan tum basliklari yazdirin
            Assert that "delectus aut autem" is one of the titles whose id is less than 5 ==> id si 5 den kucuk olan datalarin birinin
            basliginin "delectus aut autem" icerdigini dogrulayin
     */
    @Test
    public void get01() {
        // Set The URL
        spec.pathParam("first", "todos   ");
        // Expected Data
        //3.Send The Request and get the Response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();
        //do asserttion
        //1)Status code is 200 == > Status kodu 200 olmali
       response.then().assertThat().statusCode(200 );
        //2)Print al<l ids greater than 190 on the console ==> id si 190 dan buyuk olanlari konsola yazdirin
        //Assert that there are 10 ids greater than 190 == > 10 tane id nin 190 dan buyuk oldugunu dogrulayin
        JsonPath json=response.jsonPath();
        List<Integer> idler=json.getList("findAll{it.id>190}.id");
        System.out.println("id si 190 dan buyuk olanlar :"+idler);
        //3)Print all userIds whose ids are less than 5 on the console ==> id si 5 den kucuk olan tum userid lerini konsolunu yazdirin
        List<Integer> userId=json.getList("findAll{it.id<5}.userId");
        System.out.println("id si 5 den küçük olanlar :"+userId);
        //Assert that the number of userIds whose ids are less than 5 is 4 ==> id si 5 den kucuk olan 4 tane userId oldugunu dogrulayin
       assertEquals("d si 5 den kucuk olan 4 tane değil",4,userId.size());
        //4)Print all titles whose ids are less than 5 ==> ıd si 5 den kucuk olan tum basliklari yazdirin
        List<Integer> titles=json.getList("findAll{it.id<5}.title");
        System.out.println("id si 5 den küçük olan title lar :"+titles);

        //Assert that "delectus aut autem" is one of the titles whose id is less than 5 ==> id si 5 den kucuk olan datalarin birinin
        //basliginin "delectus aut autem" icerdigini dogrulayin




    }


}
