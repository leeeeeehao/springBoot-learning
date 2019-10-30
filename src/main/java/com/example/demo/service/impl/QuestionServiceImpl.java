package com.example.demo.service.impl;

import com.example.demo.mapper.QuestionMapper;
import com.example.demo.model.Question;
import com.example.demo.service.QuestionService;
import com.example.demo.vo.QuestionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称: game-parent
 * 类名称: QuestionServiceImpl
 * 类描述: TODO
 * 创建人: leehao
 * 创建时间: 2019-10-30 14:52
 * 修改人: leehao
 * 修改时间: 2019-10-30 14:52
 * 修改备注:
 * 版权所有权: 江苏心智未来网络科技有限公司
 *
 * @version v1.0
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public void insertQuestion(String title, String content, String labels, Integer creator) {
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(content);
        question.setCreator(creator);
        question.setTags(labels);
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionMapper.insertQuestion(question);
    }
}
