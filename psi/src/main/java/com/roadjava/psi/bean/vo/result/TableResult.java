package com.roadjava.psi.bean.vo.result;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 表格数据模型
 * @author zhaodaowen
 */
@Data
public class TableResult<T> {
    /**
     * 查询出的记录
     */
    private List<T> rows = new ArrayList<>();
    /**
     * 总条数
     */
    private long total;
}
