package com.roadjava.psi.convert;

import com.roadjava.psi.bean.entity.PurchaseDO;
import com.roadjava.psi.bean.entity.PurchaseDetailDO;
import com.roadjava.psi.bean.request.purchase.PurchaseAddReq;
import com.roadjava.psi.bean.request.purchase.PurchaseDetailAddReq;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Component
public class PurchaseConvert {

    public PurchaseDO addReq2DO(PurchaseAddReq req) {
        PurchaseDO entity = new PurchaseDO();
        BeanUtils.copyProperties(req,entity);
        return entity;
    }

    public PurchaseDetailDO detailAddReq2DO(PurchaseDetailAddReq purchaseDetailAddReq) {
        PurchaseDetailDO entity = new PurchaseDetailDO();
        BeanUtils.copyProperties(purchaseDetailAddReq,entity);
        return entity;
    }
}
