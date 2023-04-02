package com.eden.linebot;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class JsonOperation {

    public String parseGptJson(String json){
        JSONObject obj = new JSONObject(json);
        JSONArray arr = obj.getJSONArray("choices");
        String result = null;

        for (int i = 0; i < arr.length(); i++) {
            result = arr.getJSONObject(i).getString("text");
            result = result.replace("\n" , "");
        }

        return result;

    }


}
