package com.roadjava.psi.handler;

import com.roadjava.psi.bean.request.goods.GoodsAddReq;
import com.roadjava.psi.bean.request.goods.GoodsSearchReq;
import com.roadjava.psi.bean.vo.GoodsVO;
import com.roadjava.psi.bean.vo.result.ResultVO;
import com.roadjava.psi.bean.vo.result.TableResult;
import com.roadjava.psi.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Slf4j
@RestController
@RequestMapping("/goods")
public class GoodsHandler {
    @Resource
    private GoodsService goodsService;
    /**
     * 新增
     */
    @PostMapping("/add")
    public ResultVO<String> add(@RequestBody @Validated GoodsAddReq req) {
        goodsService.add(req);
        return ResultVO.buildSuccess("添加成功");
    }

    /**
     * 查询表格数据
     */
    @PostMapping("/loadTable")
    public ResultVO<TableResult<GoodsVO>> loadTable(@RequestBody @Validated GoodsSearchReq req) {
        return goodsService.loadTable(req);
    }
    /**
     * 按id删除
     */
    @GetMapping("/deleteById")
    public ResultVO<String> deleteById(Long id) {
        goodsService.deleteById(id);
        return ResultVO.buildSuccess("删除成功");
    }

    @PostMapping("/selectList")
    public ResultVO<List<GoodsVO>> selectList(@RequestBody GoodsSearchReq req) {
        return goodsService.selectList(req);
    }

}
