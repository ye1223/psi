package com.roadjava.psi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.roadjava.psi.bean.entity.ReturnGoodsDO;
import com.roadjava.psi.bean.request.ret.ReturnSearchReq;
import com.roadjava.psi.bean.vo.ReturnGoodsVO;

import java.util.List;

/**
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public interface ReturnGoodsMapper extends BaseMapper<ReturnGoodsDO> {
    List<ReturnGoodsVO> selectRetList(ReturnSearchReq req);
}
