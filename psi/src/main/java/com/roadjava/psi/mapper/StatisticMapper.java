package com.roadjava.psi.mapper;

import com.roadjava.psi.bean.dto.BarDTO;
import com.roadjava.psi.bean.request.statistics.ChartReq;

import java.util.List;
import java.util.Map;

public interface StatisticMapper {
    List<Map<String, Object>> loadTrend(ChartReq req);

    List<BarDTO> loadSaleNum4Goods(ChartReq req);

    List<BarDTO> loadSaleAmount4Goods(ChartReq req);

    List<Map<String, Object>> supplierRetCount(ChartReq req);
}
