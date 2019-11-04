package com.example.demo.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.ToString;

/**
 * 项目名称: game-parent
 * 类名称: Question
 * 类描述: TODO
 * 创建人: leehao
 * 创建时间: 2019-08-09 15:49
 * 修改人: leehao
 * 修改时间: 2019-08-09 15:49
 * 修改备注:
 * 版权所有权: 江苏心智未来网络科技有限公司
 *
 * @version v1.0
 */
@Data
@ToString
@TableName("t_question")
public class Question {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    /**
     * 问题标题
     */
    @TableField("question_title")
    private String Title;
    /**
     * 问题描述
     */
    @TableField("question_description")
    private String description;
    /**
     * 创建时间
     */
    @TableField("gmt_create")
    private long gmtCreate;
    /**
     * 修改时间
     */
    @TableField("gmt_modified")
    private long gmtModified;
    /**
     * 创建人
     */
    @TableField("question_creator")
    private String creator;
    /**
     * 评论数
     */
    @TableField("comment_count")
    private int commentCount;
    /**
     * 查看数
     */
    @TableField("view_count")
    private int viewCount;
    /**
     * 点赞数
     */
    @TableField("like_count")
    private int likeCount;
    /**
     * 问题标签
     */
    @TableField("question_tags")
    private String tags;

}
