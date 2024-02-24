package com.roadjava.psi.bean.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品信息
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
public class GoodsVO {
    /**
     * 系统编号
     */
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
     * 销售价
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
