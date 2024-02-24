package com.roadjava.psi.bean.vo;

import lombok.Data;

/**
 * 返回给前端的供应商对象
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
public class SupplierVO {
    /**
     * 系统编号
     */
    private Long id;
    /**
     * 供应商名
     */
    private String name;
}
