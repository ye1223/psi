package com.roadjava.psi.bean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 进货明细表
 * @author zhaodaowen
 */
@Data
@TableName("purchase_detail")
public class PurchaseDetailDO {
    /**
     * 系统编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * purchase表主键
     */
    private Long purchaseId;
    /**
     * 业务外键,进货批次号
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
