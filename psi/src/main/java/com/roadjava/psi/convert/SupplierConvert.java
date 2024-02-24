package com.roadjava.psi.convert;

import com.roadjava.psi.bean.entity.SupplierDO;
import com.roadjava.psi.bean.request.supplier.SupplierAddReq;
import com.roadjava.psi.bean.request.supplier.SupplierUpdateReq;
import com.roadjava.psi.bean.vo.SupplierVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Component
public class SupplierConvert {
    public SupplierDO addReq2DO(SupplierAddReq req) {
        SupplierDO entity = new SupplierDO();
        BeanUtils.copyProperties(req,entity);
        return entity;
    }

    public SupplierDO updateReq2DO(SupplierUpdateReq req) {
        SupplierDO entity = new SupplierDO();
        BeanUtils.copyProperties(req,entity);
        return entity;
    }


    public SupplierVO entity2VO(SupplierDO entity) {
        SupplierVO vo = new SupplierVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }
}
