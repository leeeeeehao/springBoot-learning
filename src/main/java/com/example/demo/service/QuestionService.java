package com.example.demo.service;

import com.example.demo.model.Question;
import com.example.demo.vo.QuestionVo;

/**
 * 项目名称: game-parent
 * 类名称: QuestionService
 * 类描述: TODO
 * 创建人: leehao
 * 创建时间: 2019-10-30 14:51
 * 修改人: leehao
 * 修改时间: 2019-10-30 14:51
 * 修改备注:
 * 版权所有权: 江苏心智未来网络科技有限公司
 *
 * @version v1.0
 */
public interface QuestionService {

    void insertQuestion(String title, String content, String labels, Integer creator);
}
