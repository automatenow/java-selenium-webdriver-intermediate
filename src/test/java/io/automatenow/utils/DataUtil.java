package io.automatenow.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.automatenow.core.BaseTest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Marco A. Cruz
 */
public class DataUtil extends BaseTest {

    @DataProvider
    public static Object[] dataProvider1() {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;

        // Read JSON file
        try {
            Object obj = parser.parse(new FileReader("src/main/resources/testData.json"));
            jsonObject = (JSONObject) obj;
        } catch (IOException | ParseException exception) {
            exception.printStackTrace();
        }

        // Array to store JSON data
        Object[] data = new Object[1];

        // Store JSON data as key/value pairs in a hashMap
        HashMap<String, String> hashMap = new LinkedHashMap<>();
        if (jsonObject != null) {
            Set<String> jsonObjKeys = jsonObject.keySet();
            for (String jsonObjKey : jsonObjKeys) {
                hashMap.put(jsonObjKey, (String) jsonObject.get(jsonObjKey));
            }
        } else {
            log.error("Error retrieving JSON data");
            throw new RuntimeException();
        }

        // Store HashMap in an array and return array
        data[0] = hashMap;
        return data;
    }

    @DataProvider
    public static Object[] dataProvider2() {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject;

        // Read JSON file
        Object obj = null;
        try {
            obj = parser.parse(new FileReader("src/main/resources/testData2.json"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        jsonObject = (JSONObject) obj;

        // Extract array data from JSONObject
        assert jsonObject != null;
        JSONArray formInfo = (JSONArray) jsonObject.get("form info");

        // String array to store JSONArray data
        String[] dataArray = new String[formInfo.size()];

        // JSONObject to read each JSONArray object
        JSONObject formInfoData;
        String inputField, checkbox, radioBtn, dropdown, email, message;

        // Get data from JSONArray and store it in String array
        for (int i = 0; i < formInfo.size(); i++) {
            formInfoData = (JSONObject) formInfo.get(i);
            inputField = (String) formInfoData.get("Input Field");
            checkbox = (String) formInfoData.get("Checkbox");
            radioBtn = (String) formInfoData.get("Radio Button");
            dropdown = (String) formInfoData.get("Dropdown");
            email = (String) formInfoData.get("Email");
            message = (String) formInfoData.get("Message");

            dataArray[i] = inputField + "," + checkbox + "," + radioBtn + "," + dropdown + "," + email + "," + message;
        }
        return  dataArray;
    }

    @DataProvider
    public static Object[][] dataProvider3() {
        return readJSON("src/main/resources/testData3.json", "data 1");
    }

    @DataProvider
    public static Object[][] dataProvider4() {
        return readJSON("src/main/resources/testData3.json", "data 2");
    }

    // This method uses the Gson library to parse JSON data
    public static Object[][] readJSON(String filename, String jsonObj) {
        File file = new File(filename);
        JsonElement jsonElement = null;

        // Parse JSON data
        try {
            jsonElement = JsonParser.parseReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert jsonElement != null;
        // Get entire JSON object
        JsonObject jsonObj1 = jsonElement.getAsJsonObject();
        // Get individual JSON array object
        JsonArray jsonArray = jsonObj1.get(jsonObj).getAsJsonArray();

        // Java array to store JSON data
        Object[][] testData = new Object[jsonArray.size()][1];

        // Read data inside JSON array and store it in Java array
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObj2 = jsonArray.get(i).getAsJsonObject();
            Map<Object, Object> map = new HashMap<>();

            for (String key : jsonObj2.keySet()) {
                String value = jsonObj2.get(key).getAsString();
                map.put(key, value);
            }
            testData[i][0] = map;
        }
        return testData;
    }
}
