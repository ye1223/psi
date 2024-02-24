package com.roadjava.psi.service;


import com.roadjava.psi.bean.request.statistics.ChartReq;
import com.roadjava.psi.bean.vo.result.ResultVO;
import com.roadjava.psi.bean.vo.statistics.BarVO;
import com.roadjava.psi.bean.vo.statistics.LineVO;
import com.roadjava.psi.bean.vo.statistics.PieVO;

/**
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public interface StatisticService {

    ResultVO<BarVO> loadSaleNum4Goods(ChartReq req);

    ResultVO<LineVO> loadTrend(ChartReq req);

    ResultVO<PieVO> loadRet4Supplier(ChartReq req);

    ResultVO<BarVO> loadSaleAmount4Goods(ChartReq req);
}
