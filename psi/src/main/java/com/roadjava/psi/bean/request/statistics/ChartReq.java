package com.roadjava.psi.bean.request.statistics;

import lombok.Data;

import java.util.List;

/**
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
public class ChartReq {
    private Integer graduateYear;
    private List<Long> majorIds;
    private List<Long> goodsIds;
    private String startDate;
}
