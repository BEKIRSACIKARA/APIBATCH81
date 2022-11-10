package test_data;

import java.util.HashMap;
import java.util.Map;

public class ReqresTestData {

    public Map<String, String> requestDataMetod(String name, String job) {
        if (job != null) {
            Map<String, String> regresDataMap = new HashMap<>();
            regresDataMap.put("name", name);
            regresDataMap.put("job", job);


            return regresDataMap;
        }
        Map<String, String> regresDataMap = new HashMap<>();
        regresDataMap.put("name", name);
        return regresDataMap;
    }

    public String expectedDataInString(String name) {

        String expectedData = "{\n" +
                "             \"name\":\"" + name + "\" \n" + "}";
        return expectedData;
    }
}