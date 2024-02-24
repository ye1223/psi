package com.roadjava.psi.bean.request.supplier;

import com.roadjava.psi.bean.request.BaseSearchReq;
import lombok.Data;

/**
 * 供应商
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
public class SupplierSearchReq extends BaseSearchReq {
    /**
     * 供应商名字
     */
    private String name;
}
