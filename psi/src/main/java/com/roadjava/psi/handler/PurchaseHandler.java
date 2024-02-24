package com.roadjava.psi.handler;

import com.roadjava.psi.bean.constants.Constants;
import com.roadjava.psi.bean.request.purchase.PurchaseAddReq;
import com.roadjava.psi.bean.request.purchase.PurchaseSearchReq;
import com.roadjava.psi.bean.request.purchase.PurchaseUpdateStatusReq;
import com.roadjava.psi.bean.vo.PurchaseVO;
import com.roadjava.psi.bean.vo.result.ResultVO;
import com.roadjava.psi.bean.vo.result.TableResult;
import com.roadjava.psi.service.PurchaseService;
import com.roadjava.psi.util.NoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Slf4j
@RestController
@RequestMapping("/purchase")
public class PurchaseHandler {
    @Resource
    private PurchaseService purchaseService;
    /**
     * 新增
     */
    @PostMapping("/add")
    public ResultVO<String> add(@RequestBody @Validated PurchaseAddReq req) {
        purchaseService.add(req);
        return ResultVO.buildSuccess("添加成功");
    }
    /**
     * 更新
     */
    @PostMapping("/updateStatus")
    public ResultVO<String> updateStatus(@RequestBody @Validated PurchaseUpdateStatusReq req) {
        purchaseService.updateStatus(req);
        return ResultVO.buildSuccess("更新成功");
    }

    /**
     * 查询表格数据
     */
    @PostMapping("/loadTable")
    public ResultVO<TableResult<PurchaseVO>> loadTable(@RequestBody @Validated PurchaseSearchReq req) {
        return purchaseService.loadTable(req);
    }
    /**
     * 按id删除
     */
    @GetMapping("/deleteById")
    public ResultVO<String> deleteById(Long id) {
        purchaseService.deleteById(id);
        return ResultVO.buildSuccess("删除成功");
    }
    /**
     * 得到单号
     */
    @GetMapping("/getNo")
    public ResultVO<String> getNo() {
        return ResultVO.buildSuccess(NoUtil.getNo(Constants.PRE_PURCHASE));
    }
}
