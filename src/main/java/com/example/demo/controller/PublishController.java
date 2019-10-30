package com.example.demo.controller;

import com.example.demo.mapper.QuestionMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Question;
import com.example.demo.model.User;
import com.example.demo.service.QuestionService;
import com.example.demo.vo.QuestionVo;
import com.sun.xml.internal.rngom.parse.host.Base;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author leehao
 * @version 1.0
 * @date 2019-07-24 16:34
 */
@Controller
@Slf4j
public class PublishController extends BaseController {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/doPublish")
    public String doPublish(@RequestParam("title") String title,
                            @RequestParam("content") String content,
                            @RequestParam("labels") String labels,
                            HttpServletRequest request, Model model) {

        try {
            log.info("用户登陆验证");
            User user = getUserInfoByCookie(request);
            //如果用户为空
            if (user == null) {
                log.warn("用户未登录");
                model.addAttribute("error", "用户未登录");
                return "publish";
            }else{
                questionService.insertQuestion(title, content, labels, user.getId());
                log.info("发布问题成功");
                return "redirect:/";
            }
        } catch (Exception e) {
            log.error("发生错误");
            model.addAttribute("error", "发生错误");
            return "publish";
        }
    }

}
