package com.roadjava.psi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.roadjava.psi.bean.entity.UserDO;
import com.roadjava.psi.bean.enums.RoleEnum;
import com.roadjava.psi.bean.request.user.LoginReq;
import com.roadjava.psi.bean.request.user.UpdatePwdReq;
import com.roadjava.psi.bean.request.user.UserAddReq;
import com.roadjava.psi.bean.request.user.UserSearchReq;
import com.roadjava.psi.bean.vo.UserVO;
import com.roadjava.psi.bean.vo.result.ResultVO;
import com.roadjava.psi.bean.vo.result.TableResult;
import com.roadjava.psi.convert.UserConvert;
import com.roadjava.psi.ex.BizEx;
import com.roadjava.psi.mapper.UserMapper;
import com.roadjava.psi.service.UserService;
import com.roadjava.psi.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserConvert userConvert;
    @Override
    public ResultVO<String> login(LoginReq req) {
        LambdaQueryWrapper<UserDO> query = new LambdaQueryWrapper<>();
        query.eq(UserDO::getUserName,req.getUserName())
                .eq(UserDO::getPwd,req.getPwd());
        UserDO userDO = userMapper.selectOne(query);
        if (userDO == null) {
            return ResultVO.buildFailure("用户名或密码不正确");
        }
        // 用去掉密码后的userVO 生成token并返回
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userDO,userVO);
        userVO.setRoleName(RoleEnum.getEnum(userVO.getRoleId()).getRoleName());
        String token = JwtUtil.getToken(userVO);
        return ResultVO.buildSuccess(token);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(UserAddReq req) {
        UserDO entity = userConvert.addReq2DO(req);
        userMapper.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePwd(UpdatePwdReq req) {
        UserDO userDO = userMapper.selectById(req.getId());
        if (userDO == null) {
            throw new BizEx("用户不存在");
        }
        if (!req.getOldPwd().equals(userDO.getPwd())) {
            throw new BizEx("原始密码不正确");
        }
        UserDO userToUpdate = userConvert.updatePwdReq2DO(req);
        userMapper.updateById(userToUpdate);
    }

    @Override
    public ResultVO<TableResult<UserVO>> loadTable(UserSearchReq req) {
        IPage<UserDO> page = new Page<>(req.getPageNow(),req.getPageSize());
        LambdaQueryWrapper<UserDO> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(req.getUserName())) {
            queryWrapper.like(UserDO::getUserName,req.getUserName());
        }
        queryWrapper.orderByDesc(UserDO::getId);
        IPage<UserDO> pageResult = userMapper.selectPage(page, queryWrapper);
        List<UserDO> records = pageResult.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return ResultVO.buildEmptySuccess();
        }
        List<UserVO> voList = records.stream()
                .map(userDO -> userConvert.entity2vo(userDO))
                .collect(Collectors.toList());
        // 设置表格结果并返回
        TableResult<UserVO> tableResult = new TableResult<>();
        tableResult.setTotal(pageResult.getTotal());
        tableResult.setRows(voList);
        return ResultVO.buildSuccess(tableResult);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long userId) {
        userMapper.deleteById(userId);
    }

    @Override
    public ResultVO<UserVO> getUserById(UserSearchReq req) {
        UserDO userDO = userMapper.selectById(req.getUserId());
        UserVO userVO = userConvert.entity2vo(userDO);
        return ResultVO.buildSuccess(userVO);
    }
}
