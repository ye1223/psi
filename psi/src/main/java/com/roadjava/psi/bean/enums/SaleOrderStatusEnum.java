package com.roadjava.psi.bean.enums;

/**
 * 进货单状态
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public enum SaleOrderStatusEnum {
    DONE(0,"已完成"),
    REFUND_ED(1,"已退款");

    SaleOrderStatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }
    private Integer status;
    private String desc;

    public Integer getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }

    public static SaleOrderStatusEnum getEnum(Integer status) {
        if (status == null) {
            return null;
        }
        for (SaleOrderStatusEnum e : values()) {
            if (e.status.equals(status)) {
                return e;
            }
        }
        return null;
    }
}
