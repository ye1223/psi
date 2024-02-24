package com.roadjava.psi.bean.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 进货明细表
 * @author zhaodaowen
 */
@Data
public class ReturnGoodsDetailVO {
    /**
     * 系统编号
     */
    private Long id;
    /**
     * 退货表批次号
     */
    private String retNo;
    /**
     * 商品表的主键
     */
    private Long goodsId;
    /**
     * 商品名
     */
    private String goodsName;
    private String unit;
    /**
     * 买入价格
     */
    private BigDecimal returnPrice;
    /**
     * 采购数量
     */
    private Integer num;
    /**
     * 供应商表的主键
     */
    private Long supplierId;
    /**
     * 供应商名
     */
    private String supplierName;
}
