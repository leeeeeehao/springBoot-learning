package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author leehao
 * @version 1.0
 * @date 2019/7/16 15:02
 */
@Controller
public class IndexController {

    @GetMapping("/index")
    public String hello(){
        return "index";
    }
}
