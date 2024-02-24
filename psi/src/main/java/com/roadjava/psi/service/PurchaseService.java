package com.roadjava.psi.service;

import com.roadjava.psi.bean.request.purchase.PurchaseAddReq;
import com.roadjava.psi.bean.request.purchase.PurchaseSearchReq;
import com.roadjava.psi.bean.request.purchase.PurchaseUpdateStatusReq;
import com.roadjava.psi.bean.vo.PurchaseVO;
import com.roadjava.psi.bean.vo.result.ResultVO;
import com.roadjava.psi.bean.vo.result.TableResult;

/**
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public interface PurchaseService {
    void add(PurchaseAddReq req);

    ResultVO<TableResult<PurchaseVO>> loadTable(PurchaseSearchReq req);

    void deleteById(Long id);

    void updateStatus(PurchaseUpdateStatusReq req);
}
