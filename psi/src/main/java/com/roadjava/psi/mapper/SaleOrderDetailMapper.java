package com.roadjava.psi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.roadjava.psi.bean.entity.SaleOrderDetailDO;
import com.roadjava.psi.bean.vo.SaleOrderDetailVO;

import java.util.List;

/**
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public interface SaleOrderDetailMapper extends BaseMapper<SaleOrderDetailDO> {

    List<SaleOrderDetailVO> selectByOrderIds(List<Long> orderIds);
}
