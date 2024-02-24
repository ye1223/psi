package com.roadjava.psi.bean.request.purchase;

import com.roadjava.psi.bean.request.BaseSearchReq;
import lombok.Data;

/**
 * 进货查询入参
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
public class PurchaseSearchReq extends BaseSearchReq {
    private String purchaseNo;
    private Integer status;
}
