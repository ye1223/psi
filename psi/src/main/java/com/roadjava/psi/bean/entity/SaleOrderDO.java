package com.roadjava.psi.bean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品销售订单
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
@TableName("sale_order")
public class SaleOrderDO {
    /**
     * 系统编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 商品总价
     */
    private BigDecimal totalAmount;
    /**
     * @see com.roadjava.psi.bean.enums.SaleOrderStatusEnum
     */
    private Integer status;

    /**
     * 创建日期
     */
    private Date createTime;
}
