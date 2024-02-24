package com.roadjava.psi.bean.vo.statistics;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 柱状图视图对象
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
public class PieVO {
    /**
     * {value: 1048, name: '搜索引擎'}
     */
    private List<Map<String,Object>> seriesData;
}
