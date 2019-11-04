package com.example.demo.service;

/**
 * 项目名称: game-parent
 * 类名称: AuthorizeService
 * 类描述: TODO
 * 创建人: leehao
 * 创建时间: 2019-11-04 14:06
 * 修改人: leehao
 * 修改时间: 2019-11-04 14:06
 * 修改备注:
 * 版权所有权: 江苏心智未来网络科技有限公司
 *
 * @version v1.0
 */
public interface AuthorizeService {

    String authorizeUser(String code, String state);
}
