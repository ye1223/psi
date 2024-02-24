package com.roadjava.psi.bean.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 退款明细表
 * @author zhaodaowen
 */
@Data
@TableName("refund_detail")
public class RefundDetailVO {
    /**
     * 系统编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 退款表主键
     */
    private Long refundId;
    /**
     * 商品id
     */
    private Long goodsId;
    /**
     * 商品名
     */
    private String goodsName;
    /**
     * 卖出价格
     */
    private BigDecimal refundPrice;
    /**
     * 购买数量
     */
    private Integer num;
}
