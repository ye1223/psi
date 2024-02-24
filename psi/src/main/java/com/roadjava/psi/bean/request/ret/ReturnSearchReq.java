package com.roadjava.psi.bean.request.ret;

import com.roadjava.psi.bean.request.BaseSearchReq;
import lombok.Data;

/**
 * 退货查询入参
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
public class ReturnSearchReq extends BaseSearchReq {
    private String fuzzyRetNo;
    private Integer status;
}
