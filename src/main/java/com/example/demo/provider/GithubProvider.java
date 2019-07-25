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

    /**
     * 用户问题回答
     * @param userDcr,userQuestion,type 0-支持重复句式异常 1-剔除所有重复字符串
     * @return result
     */
    public static UserResult getAnswer(String userDcr,String userQuestion,int type){
//        Log.i("TAG","用户话语:" + userDcr + "," + "用户提问:" + userQuestion);
//        Properties proper = ProperTies.getProperties(ApplicationUtil.getContext());
        userCstList.add(userDcr);
        int count = 0;//重复数
        int startIndex = 0;//相同字符起始位置
        int endIndex = 0;//相同字符结束位置
        String result = "";//结果字符串s
        String screenText = "";
        UserResult userResult = new UserResult();
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
                userResult.setOriginalDcr(result);
                return userResult;
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
                    String backupCopy = userDcr;//备份用户话语
                    //循环用户话语字符串，将重复的依次剔除
                    for(int z=0;z<resultArray.length;z++){
                        userDcr = userDcr.replace(String.valueOf(resultArray[z]),"");
                    }
                    resultArray = userDcr.toCharArray();
                    int screentSize = 0;
                    int compareNum = 0;
                    for(int q = 0;q<dcrArray.length;q++){
                        compareNum = screentSize;
                        for(int y = 0; y<resultArray.length;y++){
                            if(dcrArray[q]==resultArray[y]){
                                screenText += String.valueOf(resultArray[y]);
                                if(startIndex==0){
                                    startIndex = q;
                                }
                                screentSize++;
                                break;
                        }
                    }
                        //在找到相同字符下一个字符也匹配上的时候执行下面的语句
                        if(startIndex!=0&&screentSize==compareNum){
                            endIndex = q;
                            result = backupCopy.substring(startIndex,endIndex);
                            userResult.getResultList().add(result);
                            startIndex = 0;
                            screenText = "";
                            result = "";
                        }
                        else if(q==dcrArray.length-1){
                            if(screenText.equals("")){
                                
                            }
                            else{
                                 userResult.getResultList().add(screenText);
                            }
                        }
                    }
                    userResult.setOriginalDcr(backupCopy);
                }
                if(userResult.getResultList().size()>=3){
                    userResult.setOriginalDcr("大哥你问的问题太多啦");
                }
                System.out.println(userResult.toString());
                return userResult;
                default:
                    System.out.println("输入type错误");
                    return null;
        }
    }

    public static List<String> getSessionLIst(){
        return userCstList;
    }

    public static void main(String[] args) {
        GithubProvider.getAnswer("我叫李浩喜欢唱跳rap20岁了","你叫什么名字喜欢干什么几岁？",1);
    }
}
