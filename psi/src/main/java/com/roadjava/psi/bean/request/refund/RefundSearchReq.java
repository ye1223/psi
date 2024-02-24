package com.roadjava.psi.bean.request.refund;

import com.roadjava.psi.bean.request.BaseSearchReq;
import lombok.Data;

/**
 * 销售订单查询入参
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
public class RefundSearchReq extends BaseSearchReq {
    private String fuzzyRefundNo;
}
