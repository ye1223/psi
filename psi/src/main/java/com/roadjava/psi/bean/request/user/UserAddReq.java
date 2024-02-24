package com.roadjava.psi.bean.request.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 新增用户请求参数
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
public class UserAddReq {
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
    /**
     * 角色id
     * @see com.roadjava.psi.bean.enums.RoleEnum
     */
    @NotNull
    private Integer roleId;
}
