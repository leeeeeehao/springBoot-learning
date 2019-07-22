package com.example.demo.dto;

import lombok.Data;

/**
 * @author leehao
 * @version 1.0
 * @date 2019/7/22 10:48
 */
@Data
public class AccessTokenDto {
    /**
     * 客户端id
     */
    private String client_id;
    /**
     * 客户机密
     */
    private String client_secret;
    /**
     * code码，github第一步的回复
     */
    private String code;
    /**
     * 重定向地址
     */
    private String redirect_uri;
    /**
     * 随机字符串
     */
    private String state;
}
