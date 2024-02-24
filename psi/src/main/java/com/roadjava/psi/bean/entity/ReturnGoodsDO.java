package com.roadjava.psi.bean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 进货退货信息
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
@TableName("return_goods")
public class ReturnGoodsDO {
    /**
     * 系统编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 进货号
     */
    private String purchaseNo;
    /**
     * 退货批次号
     */
    private String retNo;
    /**
     * 经办人
     */
    private Long operatorId;
    /**
     * 0:待审核 1:审核通过
     */
    private Integer status;
    /**
     * 退货日期
     */
    private Date returnDate;
}
