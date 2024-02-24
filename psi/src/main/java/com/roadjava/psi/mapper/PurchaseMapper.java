package com.roadjava.psi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.roadjava.psi.bean.entity.PurchaseDO;
import com.roadjava.psi.bean.request.purchase.PurchaseSearchReq;
import com.roadjava.psi.bean.vo.PurchaseVO;

import java.util.List;

/**
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public interface PurchaseMapper extends BaseMapper<PurchaseDO> {
    List<PurchaseVO> selectPurchaseList(PurchaseSearchReq req);

    PurchaseVO selectWithDetail(PurchaseDO purchaseQuery);
}
