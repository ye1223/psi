package com.roadjava.psi.bean.request.sale;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhaodaowen
 */
@Data
public class SaleOrderDetailReq {
    /**
     * 商品id
     */
    private Long goodsId;
    /**
     * 卖出价格
     */
    private BigDecimal salePrice;
    /**
     * 购买数量
     */
    private Integer num;
}
