package com.roadjava.psi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.roadjava.psi.bean.entity.SaleOrderDO;
import com.roadjava.psi.bean.request.sale.SaleOrderSearchReq;
import com.roadjava.psi.bean.vo.SaleOrderVO;

import java.util.List;

/**
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public interface SaleOrderMapper extends BaseMapper<SaleOrderDO> {
    List<SaleOrderVO> selectOrderList(SaleOrderSearchReq req);
}
