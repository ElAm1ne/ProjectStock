package com.example.ProjectStock.Helpers;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class JsonToHashMap {
    public static Map<String, Object> jsonToHashMap(JSONObject json) {
        Map<String, Object> hashMap = new HashMap<>();
        json.keySet().forEach(keyStr -> {
            Object keyValue = json.get(keyStr);
            if (keyValue instanceof JSONObject) {
                // If the value is a nested JSON object, recursively convert it to a hashmap.
                keyValue = jsonToHashMap((JSONObject) keyValue);
            }
            hashMap.put(keyStr, keyValue);
        });
        return hashMap;
    }
}