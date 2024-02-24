package com.roadjava.psi.service;

import com.roadjava.psi.bean.request.ret.RetUpdateStatusReq;
import com.roadjava.psi.bean.request.ret.ReturnAddReq;
import com.roadjava.psi.bean.request.ret.ReturnSearchReq;
import com.roadjava.psi.bean.vo.ReturnGoodsVO;
import com.roadjava.psi.bean.vo.result.ResultVO;
import com.roadjava.psi.bean.vo.result.TableResult;

/**
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public interface ReturnGoodsService {
    void add(ReturnAddReq req);

    ResultVO<TableResult<ReturnGoodsVO>> loadTable(ReturnSearchReq req);

    void deleteById(Long id);
    void deleteByPurchaseNo(String purchaseNo);
    void updateStatus(RetUpdateStatusReq req);
}
