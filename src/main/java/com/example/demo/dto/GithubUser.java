package com.example.demo.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @author leehao
 * @version 1.0
 * @date 2019/7/22 14:05
 */
@Data
@ToString
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

    /**
     * 用户头像
     */
    private String avatarUrl;
}
