package com.example.demo.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.ToString;

/**
 * @author leehao
 * @version 1.0
 * @date 2019/7/23 17:20
 */
@Data
@ToString
@TableName("t_user")
public class User {

    /**
     * 用户Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    /**
     * 用户名称
     */
    @TableField("name")
    private String name;
    /**
     * 账户Id
     */
    @TableField("account_id")
    private String accountId;
    /**
     * 登录凭证
     */
    @TableField("token")
    private String token;
    /**
     * 用户头像
     */
    @TableField("avatar_url")
    private String avatarUrl;
    /**
     * 创建时间
     */
    @TableField("gmt_create")
    private Long gmtCreate;
    /**
     * 修改时间
     */
    @TableField("gmt_modified")
    private Long gmtModified;

}
