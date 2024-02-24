package com.roadjava.psi.bean.request.purchase;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 进货明细表
 * @author zhaodaowen
 */
@Data
public class PurchaseDetailAddReq {
    /**
     * 进货批次号
     */
    private String purchaseNo;
    /**
     * 商品表的主键
     */
    private Long goodsId;
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

}
