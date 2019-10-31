package com.example.demo.provider;

import com.alibaba.fastjson.JSON;
import com.example.demo.DemoApplication;
import com.example.demo.dto.AccessTokenDto;
import com.example.demo.dto.GithubUser;
import com.example.demo.model.UserResult;
import okhttp3.*;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author leehao
 * @version 1.0
 * @date 2019/7/22 10:46
 */
@Component
public class GithubProvider {

    private static List<String> userCstList = new ArrayList<>();
    /**
     * 获取AccessToken
     * @param accessTokenDto
     * @return
     */
    public String getAccessToken(AccessTokenDto accessTokenDto){
        final MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDto));
            Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                String string = response.body().string();
                String[] split = string.split("&");
                String tokenstr = split[0];
                String token = tokenstr.split("=")[1];
                return token;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
    }

    /**
     * 获取用户信息
     * @param accessToken
     * @return
     */
    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            System.out.println(string);
            GithubUser githubUser = JSON.parseObject(string,GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
