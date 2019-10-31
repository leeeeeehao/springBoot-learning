package com.example.demo.controller;

import com.example.demo.dto.QuestionDTO;
import com.example.demo.model.User;
import com.example.demo.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author leehao
 * @version 1.0
 * @date 2019/7/16 15:02
 */
@Slf4j
@Controller
public class IndexController extends BaseController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/index")
    public String index(HttpServletRequest request, Model model) {

        try{
            User user = getUserInfoByCookie(request);
            List<QuestionDTO> questionDTOList = questionService.queryQuestions();
            model.addAttribute("questions", questionDTOList);
            return "index";
        }catch (Exception e){
            log.error(e.toString());
            return "index";
        }
    }

}
