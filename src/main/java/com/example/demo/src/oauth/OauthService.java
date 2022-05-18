package com.example.demo.src.oauth;

import com.example.demo.config.BaseException;
import com.example.demo.src.oauth.model.GetKakaoLoginRes;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class OauthService {
    public GetKakaoLoginRes getKakaoToken(String code) throws BaseException {
        String requestURL = "https://kauth.kakao.com/oauth/token";
        GetKakaoLoginRes getKakaoLoginRes = new GetKakaoLoginRes();

        try {
            URL url = new URL(requestURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("POST");
            con.setDoOutput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=6883a185589df78f80330dda77f725d3");
            sb.append("&redirect_uri=http://localhost:9000/oauth/kakao");
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) { result += line; }

            JsonParser jp = new JsonParser();
            JsonElement je = jp.parse(result);

            getKakaoLoginRes.setAccessToken(je.getAsJsonObject().get("access_token").getAsString());
            getKakaoLoginRes.setRefreshToken(je.getAsJsonObject().get("refresh_token").getAsString());

            br.close();
            bw.close();
        } catch (IOException exception) {}

        return getKakaoLoginRes;
    }
}
