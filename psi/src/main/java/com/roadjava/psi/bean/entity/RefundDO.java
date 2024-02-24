package com.roadjava.psi.bean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 退款表
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
@TableName("refund")
public class RefundDO {
    /**
     * 系统编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 订单表主键
     */
    private String orderNo;
    /**
     * 退款编号
     */
    private String refundNo;
    /**
     * 退款总金额
     */
    private BigDecimal totalAmount;
    /**
     * 日期
     */
    private Date createTime;
}
