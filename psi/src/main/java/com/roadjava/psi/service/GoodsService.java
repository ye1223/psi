package com.roadjava.psi.service;

import com.roadjava.psi.bean.request.goods.GoodsAddReq;
import com.roadjava.psi.bean.request.goods.GoodsSearchReq;
import com.roadjava.psi.bean.vo.GoodsVO;
import com.roadjava.psi.bean.vo.result.ResultVO;
import com.roadjava.psi.bean.vo.result.TableResult;

import java.util.List;

public interface GoodsService {
    void add(GoodsAddReq req);

    ResultVO<TableResult<GoodsVO>> loadTable(GoodsSearchReq req);

    void deleteById(Long id);

    /**
     * 查询商品列表
     * @param req
     * @return
     */
    ResultVO<List<GoodsVO>> selectList(GoodsSearchReq req);
}
