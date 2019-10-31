package com.example.demo.dto;

import com.example.demo.model.User;
import lombok.Data;
import lombok.ToString;

/**
 * 项目名称: game-parent
 * 类名称: QuestionDTO
 * 类描述: TODO
 * 创建人: leehao
 * 创建时间: 2019-10-31 11:06
 * 修改人: leehao
 * 修改时间: 2019-10-31 11:06
 * 修改备注:
 * 版权所有权: 江苏心智未来网络科技有限公司
 *
 * @version v1.0
 */
@Data
@ToString
public class QuestionDTO {
    /**
     * 主键
     */
    private int id;
    /**
     * 问题标题
     */
    private String title;
    /**
     * 问题描述
     */
    private String description;
    /**
     * 创建时间
     */
    private long gmtCreate;
    /**
     * 修改时间
     */
    private long gmtModified;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 评论数
     */
    private int commentCount;
    /**
     * 查看数
     */
    private int viewCount;
    /**
     * 点赞数
     */
    private int likeCount;
    /**
     * 问题标签
     */
    private String tags;

    /**
     * 用户头像
     */
    private String avatarUrl;

}
