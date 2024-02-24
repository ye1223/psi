package com.roadjava.psi.bean.request.goods;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author zhaodaowen
 */
@Data
public class GoodsAddReq {
    /**
     * 商品名称
     */
    @NotBlank
    private String name;
    /**
     * 产地
     */
    private String bornPlace;
    /**
     * 销售价
     */
    @NotNull
    private BigDecimal salePrice;
    /**
     * 单位
     */
    @NotBlank
    private String unit;
    /**
     * 规格
     */
    @NotBlank
    private String specifications;
}
