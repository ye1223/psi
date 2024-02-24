package com.roadjava.psi.bean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 进货信息
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
@TableName("purchase")
public class PurchaseDO {
    /**
     * 系统编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 进货批次号
     */
    private String purchaseNo;
    /**
     * 经办人id
     */
    private Long operatorId;
    /**
     * 0:待审核 1:审核通过 2 审核拒绝
     */
    private Integer status;
    /**
     * 买入日期
     */
    private Date purchaseDate;

}
