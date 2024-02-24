package com.roadjava.psi.bean.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 进货明细表
 * @author zhaodaowen
 */
@Data
public class PurchaseDetailVO {
    /**
     * 系统编号
     */
    private Long id;
    /**
     * 进货表主键
     */
    private String purchaseNo;
    /**
     * 商品表的主键
     */
    private Long goodsId;
    private String goodsName;
    private String unit;
    /**
     * 买入价格
     */
    private BigDecimal buyPrice;
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
