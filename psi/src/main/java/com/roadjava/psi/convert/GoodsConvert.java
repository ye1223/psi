package com.roadjava.psi.convert;

import com.roadjava.psi.bean.entity.GoodsDO;
import com.roadjava.psi.bean.request.goods.GoodsAddReq;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Component
public class GoodsConvert {

    public GoodsDO addReq2DO(GoodsAddReq req) {
        GoodsDO entity = new GoodsDO();
        BeanUtils.copyProperties(req,entity);
        return entity;
    }
}
