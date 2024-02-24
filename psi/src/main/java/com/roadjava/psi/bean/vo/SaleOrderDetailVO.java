package com.roadjava.psi.bean.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhaodaowen
 */
@Data
public class SaleOrderDetailVO {
    /**
     * 系统编号
     */
    private Long id;
    /**
     * 订单表外键
     */
    private Long orderId;
    /**
     * 商品id
     */
    private Long goodsId;
    /**
     * 商品名
     */
    private String goodsName;
    private String unit;
    /**
     * 卖出价格
     */
    private BigDecimal salePrice;
    /**
     * 购买数量
     */
    private Integer num;
}
