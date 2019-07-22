package com.example.demo.controller;

import com.example.demo.dto.AccessTokenDto;
import com.example.demo.dto.GithubUser;
import com.example.demo.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author leehao
 * @version 1.0
 * @date 2019/7/22 10:36
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name= "code") String code,
                           @RequestParam(name = "state") String state){
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setClient_id("a0a93bf028d2d2f6ef9d");
        accessTokenDto.setClient_secret("6bf98c57f54525edc27384293cca1d38b63ab711");
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri("http://localhost:8080/callback");
        accessTokenDto.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDto);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
