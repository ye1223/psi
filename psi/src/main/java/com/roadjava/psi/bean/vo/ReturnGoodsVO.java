package com.roadjava.psi.bean.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 进货信息
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
public class ReturnGoodsVO {
    private Long id;
    /**
     * 进货批次号
     */
    private String purchaseNo;
    /**
     * 退货号
     */
    private String retNo;
    /**
     * 经办人
     */
    private Long operatorId;
    /**
     * 经办人姓名
     */
    private String operatorName;
    /**
     * @see com.roadjava.psi.bean.enums.RetStatusEnum
     */
    private Integer status;
    /**
     * 状态描述
     */
    private String statusDesc;
    /**
     * 退货日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date returnDate;
    private List<ReturnGoodsDetailVO> detailList;
}
