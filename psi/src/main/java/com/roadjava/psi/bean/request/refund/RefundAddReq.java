package com.roadjava.psi.bean.request.refund;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author zhaodaowen
 */
@Data
public class RefundAddReq {
    @NotEmpty
    private List<String> orderNoList;
}
