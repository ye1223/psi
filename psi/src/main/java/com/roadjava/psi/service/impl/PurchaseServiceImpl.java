package com.roadjava.psi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.roadjava.psi.bean.entity.GoodsDO;
import com.roadjava.psi.bean.entity.PurchaseDO;
import com.roadjava.psi.bean.entity.PurchaseDetailDO;
import com.roadjava.psi.bean.enums.PurchaseStatusEnum;
import com.roadjava.psi.bean.request.purchase.PurchaseAddReq;
import com.roadjava.psi.bean.request.purchase.PurchaseDetailAddReq;
import com.roadjava.psi.bean.request.purchase.PurchaseSearchReq;
import com.roadjava.psi.bean.request.purchase.PurchaseUpdateStatusReq;
import com.roadjava.psi.bean.vo.PurchaseVO;
import com.roadjava.psi.bean.vo.result.ResultVO;
import com.roadjava.psi.bean.vo.result.TableResult;
import com.roadjava.psi.convert.PurchaseConvert;
import com.roadjava.psi.ex.BizEx;
import com.roadjava.psi.mapper.GoodsMapper;
import com.roadjava.psi.mapper.PurchaseDetailMapper;
import com.roadjava.psi.mapper.PurchaseMapper;
import com.roadjava.psi.service.PurchaseService;
import com.roadjava.psi.service.ReturnGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Resource
    private PurchaseMapper purchaseMapper;
    @Resource
    private PurchaseDetailMapper purchaseDetailMapper;
    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private PurchaseConvert purchaseConvert;
    @Resource
    private ReturnGoodsService returnGoodsService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(PurchaseAddReq req) {
        PurchaseDO purchaseDO = purchaseConvert.addReq2DO(req);
        purchaseMapper.insert(purchaseDO);
        // 插入进货明细
        for (PurchaseDetailAddReq purchaseDetailAddReq : req.getDetailList()) {
            PurchaseDetailDO detailDO = purchaseConvert.detailAddReq2DO(purchaseDetailAddReq);
            // 设置外键和业务关键唯一键
            detailDO.setPurchaseId(purchaseDO.getId());
            detailDO.setPurchaseNo(purchaseDO.getPurchaseNo());
            purchaseDetailMapper.insert(detailDO);
        }
    }

    @Override
    public ResultVO<TableResult<PurchaseVO>> loadTable(PurchaseSearchReq req) {
        List<PurchaseVO> voList = purchaseMapper.selectPurchaseList(req);
        populateBean(voList);
        // 只需要对主表进行条数统计即可
        LambdaQueryWrapper<PurchaseDO> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(req.getPurchaseNo())) {
            queryWrapper.like(PurchaseDO::getPurchaseNo,req.getPurchaseNo());
        }
        Long count = purchaseMapper.selectCount(queryWrapper);

        TableResult<PurchaseVO> tableResult = new TableResult<>();
        tableResult.setTotal(count);
        tableResult.setRows(voList);
        return ResultVO.buildSuccess(tableResult);
    }

    private void populateBean(List<PurchaseVO> voList) {
        if (CollectionUtils.isNotEmpty(voList)) {
            voList.forEach(vo -> Optional.ofNullable(PurchaseStatusEnum.getEnum(vo.getStatus()))
                .ifPresent(e -> vo.setStatusDesc(e.getDesc())));
            // 即便主表已经排序,left join 后顺序也会改变,需要对查出来的结果排序
            voList.sort((o1, o2) -> o2.getId().compareTo(o1.getId()));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        PurchaseDO purchaseDO = purchaseMapper.selectById(id);
        // 查到该进货单的明细，将对应的商品的库存去掉
        LambdaQueryWrapper<PurchaseDetailDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PurchaseDetailDO::getPurchaseId,id);
        if (PurchaseStatusEnum.AGREED.getStatus().equals(purchaseDO.getStatus())) {
            List<PurchaseDetailDO> detailDOList = purchaseDetailMapper.selectList(queryWrapper);
            for (PurchaseDetailDO detailDO : detailDOList) {
                // 查到该商品并减少库存,如果库存不够减,则不能删除进货单
                GoodsDO existedGoods = goodsMapper.selectById(detailDO.getGoodsId());
                // 避免商品删除后报npe
                if(existedGoods == null) {
                    continue;
                }
                int remainStock = existedGoods.getStock() - detailDO.getNum();
                if (remainStock < 0) {
                    throw new BizEx("库存不够,无法删除进货单");
                }
                existedGoods.setStock(remainStock);
                goodsMapper.updateById(existedGoods);
            }
        }
        // 删除明细
        purchaseDetailMapper.delete(queryWrapper);
        // 删除进货退货
        returnGoodsService.deleteByPurchaseNo(purchaseDO.getPurchaseNo());
        // 删除进货
        purchaseMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStatus(PurchaseUpdateStatusReq req) {
        PurchaseDO purchaseDO = new PurchaseDO();
        purchaseDO.setId(req.getId());
        purchaseDO.setStatus(req.getStatus());
        purchaseMapper.updateById(purchaseDO);
        //审核通过时增加商品库存
        if (PurchaseStatusEnum.AGREED.getStatus().equals(req.getStatus())) {
            // 查到该进货单的商品明细
            LambdaQueryWrapper<PurchaseDetailDO> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(PurchaseDetailDO::getPurchaseId,req.getId());
            List<PurchaseDetailDO> purchaseDetailDOList = purchaseDetailMapper.selectList(queryWrapper);
            for (PurchaseDetailDO purchaseDetailDO : purchaseDetailDOList) {
                // 并分别增加库存
                GoodsDO existedGoods = goodsMapper.selectById(purchaseDetailDO.getGoodsId());
                if(existedGoods == null) {
                    continue;
                }
                existedGoods.setStock(existedGoods.getStock() + purchaseDetailDO.getNum());
                goodsMapper.updateById(existedGoods);
            }
        }
    }
}
