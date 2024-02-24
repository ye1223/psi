package com.roadjava.psi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.roadjava.psi.bean.entity.ReturnGoodsDetailDO;
import com.roadjava.psi.bean.vo.ReturnGoodsDetailVO;

import java.util.List;

/**
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public interface ReturnGoodsDetailMapper extends BaseMapper<ReturnGoodsDetailDO> {

    List<ReturnGoodsDetailVO> selectByRetNo(List<String> retNos);
}
