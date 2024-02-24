package com.roadjava.psi.bean.request.ret;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author zhaodaowen
 */
@Data
public class RetUpdateStatusReq {
    @NotNull
    private Long id;
    /**
     * 0:待审核 1:审核通过 2 审核拒绝
     */
    @NotNull
    private Integer status;
}
