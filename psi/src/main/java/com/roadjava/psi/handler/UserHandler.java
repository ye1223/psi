package com.roadjava.psi.handler;

import com.roadjava.psi.bean.request.user.LoginReq;
import com.roadjava.psi.bean.request.user.UpdatePwdReq;
import com.roadjava.psi.bean.request.user.UserAddReq;
import com.roadjava.psi.bean.request.user.UserSearchReq;
import com.roadjava.psi.bean.vo.UserVO;
import com.roadjava.psi.bean.vo.result.ResultVO;
import com.roadjava.psi.bean.vo.result.TableResult;
import com.roadjava.psi.context.UserContext;
import com.roadjava.psi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserHandler {

    @Resource
    private UserService userService;
    /**
     * 登录
     * @return 用户token
     */
    @PostMapping("/login")
    public ResultVO<String> login(@RequestBody @Validated LoginReq req) {
        return userService.login(req);
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    public ResultVO<String> add(@RequestBody @Validated UserAddReq req) {
        userService.add(req);
        return ResultVO.buildSuccess("添加成功");
    }

    /**
     * 修改密码
     */
    @PostMapping("/updatePwd")
    public ResultVO<String> updatePwd(@RequestBody @Validated UpdatePwdReq req) {
        userService.updatePwd(req);
        return ResultVO.buildSuccess("密码修改成功");
    }
    /**
     * 查询用户列表
     */
    @PostMapping("/loadTable")
    public ResultVO<TableResult<UserVO>> loadTable(@RequestBody @Validated UserSearchReq req) {
        return userService.loadTable(req);
    }
    /**
     * 按id删除用户
     */
    @GetMapping("/deleteById")
    public ResultVO<String> deleteById(Long userId) {
        userService.deleteById(userId);
        return ResultVO.buildSuccess("删除成功");
    }
    /**
     * 通过token获取用户信息
     */
    @GetMapping("/getUserByToken")
    public ResultVO<UserVO> getUserByToken() {
        return ResultVO.buildSuccess(UserContext.get());
    }

    @PostMapping("/getUserById")
    public ResultVO<UserVO> getUserById(@RequestBody UserSearchReq req) {
        Long userId = req.getUserId();
        if (userId == null) {
            return ResultVO.buildFailure("用户id不能为空");
        }
        return userService.getUserById(req);
    }
}
