package com.roadjava.psi.bean.request;

import com.roadjava.psi.bean.constants.Constants;
import lombok.Data;

/**
 * @author zhaodaowen
 */
@Data
public class BaseSearchReq {
    /**
     * 当前要查询第几页
     */
    private Integer pageNow = Constants.DEFAULT_PAGE_NOW;
    /**
     * 每页的大小
     */
    private Integer pageSize = Constants.DEFAULT_PAGE_SIZE;
    /**
     * limit 10,10
     * offset:limit第一个参数
     */
    public Integer getStart() {
        return (getPageNow() - 1) * getPageSize();
    }
}
