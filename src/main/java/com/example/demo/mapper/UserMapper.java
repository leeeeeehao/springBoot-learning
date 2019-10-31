package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author leehao
 * @version 1.0
 * @date 2019/7/23 17:18
 */
@Mapper
public interface UserMapper {
    /**
     * 插入用户信息
     *
     * @param user
     */
    @Insert("insert into t_user(name,account_id,token,avatar_url,gmt_create,gmt_modified) values(#{name},#{accountId},#{token},#{avatarUrl},now(),now())")
    void insert(User user);

    /**
     * 查询用户token
     *
     * @param token
     * @return User
     */
    User findUserToken(@Param("token") String token);
}
