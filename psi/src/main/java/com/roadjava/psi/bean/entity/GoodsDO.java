package com.roadjava.psi.bean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品信息
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
@TableName("goods")
public class GoodsDO {
    /**
     * 系统编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 产地
     */
    private String bornPlace;
    /**
     * 销售价,单位:元
     */
    private BigDecimal salePrice;
    /**
     * 现有库存,默认0
     */
    private Integer stock;
    /**
     * 单位
     */
    private String unit;
    /**
     * 规格
     */
    private String specifications;
}
