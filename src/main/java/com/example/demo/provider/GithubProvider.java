package com.example.demo.provider;

import com.alibaba.fastjson.JSON;
import com.example.demo.DemoApplication;
import com.example.demo.dto.AccessTokenDto;
import com.example.demo.dto.GithubUser;
import okhttp3.*;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Properties;

/**
 * @author leehao
 * @version 1.0
 * @date 2019/7/22 10:46
 */
@Component
public class GithubProvider {

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

    /**
     * 用户问题回答
     * @param userDcr,userQuestion,type 0-支持重复句式异常 1-剔除所有重复字符串
     * @return result
     */
    public static String getAnswer(String userDcr,String userQuestion,int type){
//        Log.i("TAG","用户话语:" + userDcr + "," + "用户提问:" + userQuestion);
//        Properties proper = ProperTies.getProperties(ApplicationUtil.getContext());
        int count = 0;//重复数
        String result = "";//结果字符串
//        String exceptionText = proper.getProperty("exceptionText");
        char[] dcrArray = userDcr.toCharArray(),questionArray = userQuestion.toCharArray();
        switch (type){
            case 0:
                for(int i=0;i<dcrArray.length;i++){
                    for(int j=0;j<questionArray.length;j++){
                        if(dcrArray[i]==questionArray[j]){
                            result += String.valueOf(dcrArray[i]);
                            count++;
                        }
                    }
                }
                //如果有重复,否则抛出异常答复
                if(count!=0){
                    count = 0;
                    //将重复字符串从用户话语中删除
                    result = userDcr.replace(result,"");
                    char[] resultArray = result.toCharArray();
                    for(int i=0;i<resultArray.length;i++){
                        for(int j=0;j<questionArray.length;j++){
                            if(resultArray[i]==questionArray[j]){
                                count++;
                            }
                        }
                    }
                    if(count!=0){
                        result = "哈哈换个方式问我吧";
//                Log.w("TAG","语句多次重复,抛出异常回答:" + exceptionText);
                        System.out.println(result);
                    }
                    else{
//                Log.i("TAG","成功:" + result);
                        System.out.println(result);
                    }
                }
                else{
//            Log.w("TAG","无重复部分,抛出异常回答:" + exceptionText);
                    result = "哈哈换个方式问我吧";
                    System.out.println(result);
                }
                return result;
            case 1:
                for(int i=0;i<dcrArray.length;i++){
                    for(int j=0;j<questionArray.length;j++){
                        if(dcrArray[i]==questionArray[j]){
                            result += String.valueOf(dcrArray[i]);
                            count++;

                        }
                    }
                }
                if(count==0){
                    result = "哈哈换个方式问我吧";
                    System.out.println(result);
                }
                else{
                    char[] resultArray = result.toCharArray();
                    //循环用户话语字符串，将重复的依次剔除
                    for(int z=0;z<resultArray.length;z++){
                        userDcr = userDcr.replace(String.valueOf(resultArray[z]),"");
                    }
                    result = userDcr;
                }
                System.out.println(result);
                return result;
                default:
                    System.out.println("输入type错误");
                    return null;
        }
    }

    public static void main(String[] args) {
        GithubProvider.getAnswer("我最喜欢吃香蕉","老头最喜欢吃的水果是什么？",1);
    }
}
