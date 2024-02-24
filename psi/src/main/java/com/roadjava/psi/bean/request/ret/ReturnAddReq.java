package com.roadjava.psi.bean.request.ret;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author zhaodaowen
 */
@Data
public class ReturnAddReq {
    /**
     * 进货id
     */
    @NotEmpty
    private List<String> purchaseNoList;
}
