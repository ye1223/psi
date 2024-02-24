package com.roadjava.psi.service;

import com.roadjava.psi.bean.request.user.LoginReq;
import com.roadjava.psi.bean.request.user.UpdatePwdReq;
import com.roadjava.psi.bean.request.user.UserAddReq;
import com.roadjava.psi.bean.request.user.UserSearchReq;
import com.roadjava.psi.bean.vo.UserVO;
import com.roadjava.psi.bean.vo.result.ResultVO;
import com.roadjava.psi.bean.vo.result.TableResult;

/**
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public interface UserService {
    ResultVO<String> login(LoginReq req);

    /**
     * 新增用户
     * @param req
     * @return
     */
    void add(UserAddReq req);

    /**
     * 修改密码
     * @param req
     * @return
     */
    void updatePwd(UpdatePwdReq req);

    /**
     * 查询用户表格数据
     * @param req
     * @return
     */
    ResultVO<TableResult<UserVO>> loadTable(UserSearchReq req);

    void deleteById(Long userId);

    ResultVO<UserVO> getUserById(UserSearchReq req);
}
