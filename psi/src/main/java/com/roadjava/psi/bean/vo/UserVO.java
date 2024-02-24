package com.roadjava.psi.bean.vo;

import lombok.Data;

/**
 * @author zhaodaowen
 */
@Data
public class UserVO {
    /**
     * 系统编号
     */
    private Long id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 角色id
     * @see com.roadjava.psi.bean.enums.RoleEnum
     */
    private Integer roleId;
    /**
     * 角色名称
     */
    private String roleName;
}
