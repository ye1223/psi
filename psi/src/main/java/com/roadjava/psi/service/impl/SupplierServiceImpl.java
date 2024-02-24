package com.roadjava.psi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.roadjava.psi.bean.entity.SupplierDO;
import com.roadjava.psi.bean.request.supplier.SupplierAddReq;
import com.roadjava.psi.bean.request.supplier.SupplierSearchReq;
import com.roadjava.psi.bean.request.supplier.SupplierUpdateReq;
import com.roadjava.psi.bean.vo.SupplierVO;
import com.roadjava.psi.bean.vo.result.ResultVO;
import com.roadjava.psi.bean.vo.result.TableResult;
import com.roadjava.psi.convert.SupplierConvert;
import com.roadjava.psi.mapper.SupplierMapper;
import com.roadjava.psi.service.SupplierService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SupplierServiceImpl implements SupplierService {
    @Resource
    private SupplierMapper supplierMapper;
    @Resource
    private SupplierConvert supplierConvert;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SupplierAddReq req) {
        SupplierDO entity = supplierConvert.addReq2DO(req);
        supplierMapper.insert(entity);
    }

    @Override
    public ResultVO<TableResult<SupplierVO>> loadTable(SupplierSearchReq req) {
        IPage<SupplierDO> page = new Page<>(req.getPageNow(),req.getPageSize());
        LambdaQueryWrapper<SupplierDO> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(req.getName())) {
            queryWrapper.like(SupplierDO::getName,req.getName());
        }
        queryWrapper.orderByDesc(SupplierDO::getId);
        IPage<SupplierDO> pageResult = supplierMapper.selectPage(page,queryWrapper);
        List<SupplierDO> records = pageResult.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return ResultVO.buildEmptySuccess();
        }
        List<SupplierVO> voList = records.stream()
                .map(entity -> supplierConvert.entity2VO(entity))
                .collect(Collectors.toList());
        // 设置表格结果并返回
        TableResult<SupplierVO> tableResult = new TableResult<>();
        tableResult.setTotal(pageResult.getTotal());
        tableResult.setRows(voList);
        return ResultVO.buildSuccess(tableResult);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        supplierMapper.deleteById(id);
    }

    @Override
    public ResultVO<SupplierVO> getById(Long id) {
        SupplierDO supplierDO = supplierMapper.selectById(id);
        if (supplierDO == null) {
            return ResultVO.buildFailure("id:"+id+"不存在");
        }
        return ResultVO.buildSuccess(supplierConvert.entity2VO(supplierDO));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(SupplierUpdateReq req) {
        SupplierDO supplierDO = supplierConvert.updateReq2DO(req);
        supplierMapper.updateById(supplierDO);
    }

    @Override
    public ResultVO<List<SupplierVO>> list() {
        LambdaQueryWrapper<SupplierDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(SupplierDO::getId);
        List<SupplierDO> records = supplierMapper.selectList(queryWrapper);

        if (CollectionUtils.isEmpty(records)) {
            return ResultVO.buildEmptySuccess();
        }
        List<SupplierVO> voList = records.stream()
                .map(entity -> supplierConvert.entity2VO(entity))
                .collect(Collectors.toList());
        return ResultVO.buildSuccess(voList);
    }
}
