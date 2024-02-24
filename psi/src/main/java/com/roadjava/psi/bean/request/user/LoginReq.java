package com.roadjava.psi.bean.request.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 登录请求
 * @author zhaodaowen
 */
@Data
public class LoginReq {
    /**
     * 用户名
     */
    @NotBlank
    private String userName;
    /**
     * 密码
     */
    @NotBlank
    private String pwd;
}
