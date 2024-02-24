package com.roadjava.psi.convert;

import com.roadjava.psi.bean.entity.RefundDO;
import com.roadjava.psi.bean.request.refund.RefundAddReq;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Component
public class RefundConvert {

    public RefundDO addReq2DO(RefundAddReq req) {
        RefundDO entity = new RefundDO();
        BeanUtils.copyProperties(req,entity);
        return entity;
    }
}
