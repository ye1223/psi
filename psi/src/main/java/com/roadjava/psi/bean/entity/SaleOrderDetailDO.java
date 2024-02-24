package com.roadjava.psi.bean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhaodaowen
 */
@Data
@TableName("sale_order_detail")
public class SaleOrderDetailDO {
    /**
     * 系统编号
     */
    @TableId(type = IdType.AUTO)
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
     * 卖出价格
     */
    private BigDecimal salePrice;
    /**
     * 购买数量
     */
    private Integer num;
}
