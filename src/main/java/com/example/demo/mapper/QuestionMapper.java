package com.example.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.demo.dto.QuestionDTO;
import com.example.demo.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 项目名称: game-parent
 * 类名称: QuestionMapper
 * 类描述: TODO
 * 创建人: leehao
 * 创建时间: 2019-08-09 15:48
 * 修改人: leehao
 * 修改时间: 2019-08-09 15:48
 * 修改备注:
 * 版权所有权: 江苏心智未来网络科技有限公司
 *
 * @version v1.0
 */
@Mapper
public interface QuestionMapper extends BaseMapper<Question> {

    int insertQuestion(Question question);

    List<QuestionDTO> queryQuestions();
}
