package com.example.demo.controller;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author leehao
 * @version 1.0
 * @date 2019/7/16 15:02
 */
@Controller
public class IndexController extends BaseController {

    @GetMapping("/index")
    public String index(HttpServletRequest request, Model model) {
        User user = getUserInfoByCookie(request);
        return "index";
    }

}
