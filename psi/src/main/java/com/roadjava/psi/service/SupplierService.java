package com.roadjava.psi.service;

import com.roadjava.psi.bean.request.supplier.SupplierAddReq;
import com.roadjava.psi.bean.request.supplier.SupplierSearchReq;
import com.roadjava.psi.bean.request.supplier.SupplierUpdateReq;
import com.roadjava.psi.bean.vo.SupplierVO;
import com.roadjava.psi.bean.vo.result.ResultVO;
import com.roadjava.psi.bean.vo.result.TableResult;

import java.util.List;

/**
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public interface SupplierService {
    void deleteById(Long id);

    void add(SupplierAddReq req);

    ResultVO<TableResult<SupplierVO>> loadTable(SupplierSearchReq req);

    ResultVO<SupplierVO> getById(Long id);

    void updateById(SupplierUpdateReq req);

    ResultVO<List<SupplierVO>> list();
}
