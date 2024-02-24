package com.roadjava.psi.bean.enums;

/**
 * 进货单状态
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public enum PurchaseStatusEnum {
    TO_AUDIT(0,"待审核"),
    AGREED(1,"审核通过"),
    REJECTED(2,"审核拒绝"),
    RET_ED(3,"已退货")
    ;
    PurchaseStatusEnum(Integer status,String desc) {
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

    public static PurchaseStatusEnum getEnum(Integer status) {
        if (status == null) {
            return null;
        }
        for (PurchaseStatusEnum e : values()) {
            if (e.status.equals(status)) {
                return e;
            }
        }
        return null;
    }
}
