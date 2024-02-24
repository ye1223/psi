package com.roadjava.psi.bean.request.supplier;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 供应商
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
public class SupplierUpdateReq {
    @NotNull
    private Long id;
    /**
     * 供应商名
     */
    @NotBlank
    private String name;
}
