package com.roadjava.psi.bean.request.goods;

import com.roadjava.psi.bean.request.BaseSearchReq;
import lombok.Data;

/**
 * 商品搜索入参
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
public class GoodsSearchReq extends BaseSearchReq {
    private String name;
}
