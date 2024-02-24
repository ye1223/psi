package com.roadjava.psi.service;

import com.roadjava.psi.bean.request.refund.RefundAddReq;
import com.roadjava.psi.bean.request.refund.RefundSearchReq;
import com.roadjava.psi.bean.vo.RefundVO;
import com.roadjava.psi.bean.vo.result.ResultVO;
import com.roadjava.psi.bean.vo.result.TableResult;

/**
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public interface RefundService {

    void add(RefundAddReq req);

    ResultVO<TableResult<RefundVO>> loadTable(RefundSearchReq req);
}
