package com.example.demo.dto;

import lombok.Data;

/**
 * @author leehao
 * @version 1.0
 * @date 2019/7/22 14:05
 */
@Data
public class GithubUser {
    /**
     * 用户名称
     */
    private String name;
    /**
     * 用户Id
     */
    private Long id;
    /**
     * 描述
     */
    private String bio;

    @Override
    public String toString() {
        return "GithubUser{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", bio='" + bio + '\'' +
                '}';
    }
}
