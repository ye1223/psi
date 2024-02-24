package com.roadjava.psi.handler;


import com.roadjava.psi.bean.request.statistics.ChartReq;
import com.roadjava.psi.bean.vo.result.ResultVO;
import com.roadjava.psi.bean.vo.statistics.BarVO;
import com.roadjava.psi.bean.vo.statistics.LineVO;
import com.roadjava.psi.bean.vo.statistics.PieVO;
import com.roadjava.psi.service.StatisticService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 学生控制器
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@RestController
@RequestMapping("/statistic")
@Slf4j
public class StatisticHandler {
    @Resource
    private StatisticService statisticService;

    /**
     * 商品近30天内销量Top10排行榜
     */
    @PostMapping("/loadSaleNum4Goods")
    public ResultVO<BarVO> loadSaleNum4Goods(ChartReq req){
        return statisticService.loadSaleNum4Goods(req);
    }

    /**
     * 商品近30天内销售总金额Top10排行榜
     */
    @PostMapping("/loadSaleAmount4Goods")
    public ResultVO<BarVO> loadSaleAmount4Goods(ChartReq req){
        return statisticService.loadSaleAmount4Goods(req);
    }

    /**
     * 商品近30天平均进价走势图
     */
    @PostMapping("/loadTrend") 
    public ResultVO<LineVO> loadTrend(@RequestBody ChartReq req){
        return statisticService.loadTrend(req);
    }

    /**
     * 供应商退货占比
     */
    @PostMapping("/loadRet4Supplier")
    public ResultVO<PieVO> loadRet4Supplier(ChartReq req){
        return statisticService.loadRet4Supplier(req);
    }
}
