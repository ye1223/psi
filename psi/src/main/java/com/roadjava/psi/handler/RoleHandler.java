package com.roadjava.psi.handler;

import com.roadjava.psi.bean.enums.RoleEnum;
import com.roadjava.psi.bean.vo.RoleVO;
import com.roadjava.psi.bean.vo.result.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Slf4j
@RestController
@RequestMapping("/role")
public class RoleHandler {

    /**
     * 查询角色列表
     */
    @GetMapping("/listRoles")
    public ResultVO<List<RoleVO>> loadTable() {
        List<RoleVO> list = new ArrayList<>();
        for (RoleEnum value : RoleEnum.values()) {
            if (value.equals(RoleEnum.ADMIN)) {
                // 超级管理员不能新增
                continue;
            }
            RoleVO roleVO = new RoleVO().setRoleId(value.getRoleId()).setRoleName(value.getRoleName());
            list.add(roleVO);
        }
        return ResultVO.buildSuccess(list);
    }
}
