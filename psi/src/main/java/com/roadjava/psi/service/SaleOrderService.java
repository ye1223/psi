package com.roadjava.psi.service;

import com.roadjava.psi.bean.request.sale.SaleOrderAddReq;
import com.roadjava.psi.bean.request.sale.SaleOrderSearchReq;
import com.roadjava.psi.bean.vo.SaleOrderVO;
import com.roadjava.psi.bean.vo.result.ResultVO;
import com.roadjava.psi.bean.vo.result.TableResult;

/**
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public interface SaleOrderService {

    void add(SaleOrderAddReq req);

    ResultVO<TableResult<SaleOrderVO>> loadTable(SaleOrderSearchReq req);

    SaleOrderVO detailByOrderNo(String orderNo);
}
