package com.roadjava.psi.handler;

import com.roadjava.psi.bean.constants.Constants;
import com.roadjava.psi.bean.request.refund.RefundAddReq;
import com.roadjava.psi.bean.request.refund.RefundSearchReq;
import com.roadjava.psi.bean.vo.RefundVO;
import com.roadjava.psi.bean.vo.result.ResultVO;
import com.roadjava.psi.bean.vo.result.TableResult;
import com.roadjava.psi.service.RefundService;
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
@RequestMapping("/refund")
public class RefundHandler {
    @Resource
    private RefundService refundService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public ResultVO<String> add(@RequestBody @Validated RefundAddReq req) {
        refundService.add(req);
        return ResultVO.buildSuccess("退款成功");
    }

    /**
     * 查询表格数据
     */
    @PostMapping("/loadTable")
    public ResultVO<TableResult<RefundVO>> loadTable(@RequestBody @Validated RefundSearchReq req) {
        return refundService.loadTable(req);
    }
    /**
     * 得到单号
     */
    @GetMapping("/getNo")
    public ResultVO<String> getNo() {
        return ResultVO.buildSuccess(NoUtil.getNo(Constants.PRE_REFUND));
    }
}
