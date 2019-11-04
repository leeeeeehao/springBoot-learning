package com.example.demo.controller;

import com.example.demo.service.AuthorizeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author leehao
 * @version 1.0
 * @date 2019/7/22 10:36
 */
@Controller
@Slf4j
public class AuthorizeController {

    @Autowired
    private AuthorizeService authorizeService;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state, HttpServletResponse httpServletResponse) {

        try {
            String token = authorizeService.authorizeUser(code, state);
            //登陆成功,写cookie和session
            httpServletResponse.addCookie(new Cookie("token", token));
            log.info("登陆成功");
            return "redirect:index";
        } catch (Exception e) {
            log.error(e.toString());
            return "index";
        }

    }
}
