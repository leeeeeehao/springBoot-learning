package com.example.demo.vo;

import lombok.Data;
import lombok.ToString;

/**
 * 项目名称: game-parent
 * 类名称: QuestionVo
 * 类描述: TODO
 * 创建人: leehao
 * 创建时间: 2019-10-30 14:57
 * 修改人: leehao
 * 修改时间: 2019-10-30 14:57
 * 修改备注:
 * 版权所有权: 江苏心智未来网络科技有限公司
 *
 * @version v1.0
 */
@Data
@ToString
public class QuestionVo {

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签
     */
    private String labels;

}
