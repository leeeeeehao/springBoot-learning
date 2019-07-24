package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leehao
 * @version 1.0
 * @date 2019-07-24 17:08
 */
public class UserResult {
    /**
     * 用户原始话语
     */
    private String originalDcr;
    /**
     * 回答列表
     */
    private List<String> resultList = new ArrayList<>();

    public String getOriginalDcr() {
        return originalDcr;
    }

    public void setOriginalDcr(String originalDcr) {
        this.originalDcr = originalDcr;
    }

    public List<String> getResultList() {
        return resultList;
    }

    public void setResultList(List<String> resultList) {
        this.resultList = resultList;
    }

    @Override
    public String toString() {
        return "UserResult{" +
                "originalDcr='" + originalDcr + '\'' +
                ", resultList=" + resultList +
                '}';
    }
}
