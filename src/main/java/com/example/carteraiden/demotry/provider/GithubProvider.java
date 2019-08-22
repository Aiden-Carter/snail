package com.example.carteraiden.demotry.provider;

import com.alibaba.fastjson.JSON;
import com.example.carteraiden.demotry.dto.AccessTokenDTO;
import com.example.carteraiden.demotry.dto.GithubUser;
import com.sun.org.apache.bcel.internal.generic.ARETURN;
import okhttp3.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.xml.transform.OutputKeys;
import java.io.IOException;
@Component //用于连接上下文使用
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO)
    {

     MediaType mediaType
            = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string =  response.body().string();
            String token = string.split("&")[0].split("=")[1];
            return token;
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
}
    public GithubUser getUer(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?assess_token="+ accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string,GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}