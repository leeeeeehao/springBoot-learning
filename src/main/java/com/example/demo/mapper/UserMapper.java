package com.example.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.demo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author leehao
 * @version 1.0
 * @date 2019/7/23 17:18
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据token查询用户
     *
     * @param token
     * @return User
     */
    User findUserToken(@Param("token") String token);

    User findUserToAccountId(@Param("accountId") String AccountId);
}
