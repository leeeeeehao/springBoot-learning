package com.example.demo.model;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leehao
 * @version 1.0
 * @date 2019-07-24 17:08
 */
@Data
@ToString
public class UserResult {
    /**
     * 用户原始话语
     */
    private String originalDcr;
    /**
     * 回答列表
     */
    private List<String> resultList = new ArrayList<>();

}
