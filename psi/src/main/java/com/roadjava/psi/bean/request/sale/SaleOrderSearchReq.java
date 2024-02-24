package com.roadjava.psi.bean.request.sale;

import com.roadjava.psi.bean.request.BaseSearchReq;
import lombok.Data;

/**
 * 销售订单查询入参
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
public class SaleOrderSearchReq extends BaseSearchReq {
    private String orderNo;
    /**
     * 模糊查询的订单号
     */
    private String fuzzyOrderNo;
}
