package com.roadjava.psi.bean.request.purchase;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author zhaodaowen
 */
@Data
public class PurchaseAddReq {
    /**
     * 进货批次号
     */
    @NotBlank
    private String purchaseNo;
    /**
     * 经办人id
     */
    @NotNull
    private Long operatorId;
    /**
     * 买入日期
     * springmvc默认解析的日期格式: yyyy-MM-dd'T'HH:mm:ss.SSSX
     */
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date purchaseDate;

    @NotEmpty
    private List<PurchaseDetailAddReq> detailList;
}
