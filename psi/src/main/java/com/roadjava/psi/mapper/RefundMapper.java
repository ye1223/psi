package com.roadjava.psi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.roadjava.psi.bean.entity.RefundDO;
import com.roadjava.psi.bean.request.refund.RefundSearchReq;
import com.roadjava.psi.bean.vo.RefundVO;

import java.util.List;

/**
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public interface RefundMapper extends BaseMapper<RefundDO> {
    List<RefundVO> selectRefundList(RefundSearchReq req);
}
