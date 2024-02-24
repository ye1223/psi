package com.roadjava.psi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.roadjava.psi.bean.entity.RefundDetailDO;
import com.roadjava.psi.bean.vo.RefundDetailVO;

import java.util.List;

/**
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public interface RefundDetailMapper extends BaseMapper<RefundDetailDO> {

    List<RefundDetailVO> selectByRefundIds(List<Long> refundIds);
}
