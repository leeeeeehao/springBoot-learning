package com.example.demo.service.impl;

import com.example.demo.dto.AccessTokenDto;
import com.example.demo.dto.GithubUser;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.provider.GithubProvider;
import com.example.demo.service.AuthorizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.UUID;

/**
 * 项目名称: game-parent
 * 类名称: AuthorizeServiceImpl
 * 类描述: TODO
 * 创建人: leehao
 * 创建时间: 2019-11-04 14:07
 * 修改人: leehao
 * 修改时间: 2019-11-04 14:07
 * 修改备注:
 * 版权所有权: 江苏心智未来网络科技有限公司
 *
 * @version v1.0
 */
@Service
public class AuthorizeServiceImpl implements AuthorizeService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.url}")
    private String callBackUrl;

    @Override
    public String authorizeUser(String code, String state) {
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(clientSecret);
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri(callBackUrl);
        accessTokenDto.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDto);
        GithubUser githubUser = githubProvider.getUser(accessToken);

        //检查github登陆是否有返回信息
        if (null != githubUser) {
            String token = UUID.randomUUID().toString();
            User user = userMapper.findUserToAccountId(String.valueOf(githubUser.getId()));
            //检查是否有这个用户存在
            if (null != user) {
                user.setToken(token);
                user.setGmtModified(System.currentTimeMillis());
                userMapper.updateById(user);
                //如果没有
            } else {
                User nUser = new User();
                nUser.setToken(token);
                nUser.setName(githubUser.getName());
                nUser.setAccountId(String.valueOf(githubUser.getId()));
                nUser.setGmtCreate(System.currentTimeMillis());
                nUser.setGmtModified(nUser.getGmtCreate());
                nUser.setAvatarUrl(githubUser.getAvatarUrl());
                userMapper.insert(nUser);
            }
            //登陆成功,写cookie和session
            return token;
        }
        return null;
    }
}
