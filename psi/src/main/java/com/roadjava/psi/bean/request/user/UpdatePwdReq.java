package com.roadjava.psi.bean.request.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 用户信息
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
public class UpdatePwdReq {
    /**
     * 系统编号
     */
    @NotNull
    private Long id;

    /**
     * 原始密码
     */
    @NotBlank
    private String oldPwd;

    /**
     * 修改后的密码
     */
    @NotBlank
    private String newPwd;
}
