package com.eden.linebot;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Component;


@Component
public class HttpClient {

    public String doChatGptPost(String msg , String url , String authorization) throws Exception{
        String result;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httppost = new HttpPost(url);

            // 設置HTTP請求頭部
            httppost.addHeader("Authorization", authorization);
            httppost.addHeader("Content-Type", "application/json");

            JSONObject json = new JSONObject();
            json.put("prompt", msg);
            json.put("temperature", 0.5);
            json.put("max_tokens", 1000);
            json.put("top_p", 1);
            json.put("frequency_penalty", 0);
            json.put("presence_penalty", 0);
            json.put("model", "text-davinci-003");

            StringEntity entity = new StringEntity(json.toString(), "UTF-8");
            httppost.setEntity(entity);

            // 執行HTTP POST請求
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                result = EntityUtils.toString(response.getEntity());
            } finally {
                response.close();
            }
        }finally{
            httpclient.close();
        }

        return result;
    }


}
