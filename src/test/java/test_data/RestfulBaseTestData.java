package test_data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;





public class RestfulBaseTestData {


    public Map<String,Object> expectedDataMethod(String firstname, String lastname, Integer totalprice,
                                                 Boolean depositpaid, String bookingdatesMap, String additionalneeds){

    //    Map<String, String> bookingdatesMap = new HashMap<>();
       // bookingdatesMap.put("checkin", "2013-02-23");
       // bookingdatesMap.put("checkout", "2014-10-23");





        Map<String,Object> expectedDataMap = new HashMap<>();
        expectedDataMap.put("firstname",firstname);
        expectedDataMap.put("lastname",lastname);
        expectedDataMap.put("totalprice",totalprice);
        expectedDataMap.put("depositpaid",depositpaid);
        expectedDataMap.put("bookingdates",bookingdatesMap);
        expectedDataMap.put("additionalneeds",additionalneeds);
        return expectedDataMap;
    }


}