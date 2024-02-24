package com.roadjava.psi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.roadjava.psi.bean.entity.GoodsDO;
import com.roadjava.psi.bean.request.goods.GoodsAddReq;
import com.roadjava.psi.bean.request.goods.GoodsSearchReq;
import com.roadjava.psi.bean.vo.GoodsVO;
import com.roadjava.psi.bean.vo.result.ResultVO;
import com.roadjava.psi.bean.vo.result.TableResult;
import com.roadjava.psi.convert.GoodsConvert;
import com.roadjava.psi.mapper.GoodsMapper;
import com.roadjava.psi.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class GoodsServiceImpl implements GoodsService {
    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private GoodsConvert goodsConvert;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(GoodsAddReq req) {
        GoodsDO entity = goodsConvert.addReq2DO(req);
        goodsMapper.insert(entity);
    }

    @Override
    public ResultVO<TableResult<GoodsVO>> loadTable(GoodsSearchReq req) {
        PageHelper.startPage(req);
        List<GoodsVO> voList = goodsMapper.selectGoodsList(req);
        PageInfo<GoodsVO> pageInfo = new PageInfo<>(voList);
        // 设置表格结果并返回
        TableResult<GoodsVO> tableResult = new TableResult<>();
        tableResult.setTotal(pageInfo.getTotal());
        tableResult.setRows(pageInfo.getList());
        return ResultVO.buildSuccess(tableResult);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        goodsMapper.deleteById(id);
    }

    @Override
    public ResultVO<List<GoodsVO>> selectList(GoodsSearchReq req) {
        List<GoodsVO> goodsVOS = goodsMapper.selectGoodsList(req);
        return ResultVO.buildSuccess(goodsVOS);
    }
}
