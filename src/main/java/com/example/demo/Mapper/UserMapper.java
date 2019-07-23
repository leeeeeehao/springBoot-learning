package com.example.demo.Mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author leehao
 * @version 1.0
 * @date 2019/7/23 17:18
 */
@Mapper
public interface UserMapper {
    /**
     * 插入用户信息
     * @param user
     */
    void insert(User user);
}
