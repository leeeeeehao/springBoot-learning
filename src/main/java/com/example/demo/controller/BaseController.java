package com.example.demo.controller;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 项目名称: game-parent
 * 类名称: BaseController
 * 类描述: TODO
 * 创建人: leehao
 * 创建时间: 2019-10-30 16:13
 * 修改人: leehao
 * 修改时间: 2019-10-30 16:13
 * 修改备注:
 * 版权所有权: 江苏心智未来网络科技有限公司
 *
 * @version v1.0
 */
public class BaseController {

    @Autowired
    private UserMapper userMapper;

    protected User getUserInfoByCookie(HttpServletRequest request) {

        User user = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                user = userMapper.findUserToken(token);
                //如果用户信息不为空，则添加session
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                }
                break;
            }
        }
        return user;
    }

}
