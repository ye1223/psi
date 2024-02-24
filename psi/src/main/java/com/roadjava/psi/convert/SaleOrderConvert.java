package com.roadjava.psi.convert;

import com.roadjava.psi.bean.entity.SaleOrderDO;
import com.roadjava.psi.bean.entity.SaleOrderDetailDO;
import com.roadjava.psi.bean.request.sale.SaleOrderAddReq;
import com.roadjava.psi.bean.request.sale.SaleOrderDetailReq;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Component
public class SaleOrderConvert {

    public SaleOrderDO addReq2DO(SaleOrderAddReq req) {
        SaleOrderDO entity = new SaleOrderDO();
        BeanUtils.copyProperties(req,entity);
        return entity;
    }

    public SaleOrderDetailDO detailAddReq2DO(SaleOrderDetailReq saleOrderDetailReq) {
        SaleOrderDetailDO entity = new SaleOrderDetailDO();
        BeanUtils.copyProperties(saleOrderDetailReq,entity);
        return entity;
    }
}
