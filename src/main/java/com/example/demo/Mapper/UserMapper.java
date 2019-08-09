package com.example.demo.Mapper;

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
     * @param user
     */
    @Insert("insert into t_user(name,account_id,token,gmt_create,gmt_modified) values(#{name},#{accountId},#{token},now(),now())")
    void insert(User user);

    /**
     * 查询用户token
     * @param token
     * @return User
     */
    @Select("select * from t_user where token = #{token}")
    User findUserToken(@Param("token") String token);
}
