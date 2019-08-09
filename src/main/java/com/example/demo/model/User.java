package com.example.demo.model;

import lombok.Data;

/**
 * @author leehao
 * @version 1.0
 * @date 2019/7/23 17:20
 */
@Data
public class User {
    /**
     * 用户Id
     */
    private Integer id;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 账户Id
     */
    private String accountId;
    /**
     * 登录凭证
     */
    private String token;
    /**
     * 创建时间
     */
    private Long gmtCreate;
    /**
     *修改时间
     */
    private Long gmtModified;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", accountId='" + accountId + '\'' +
                ", token='" + token + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}
