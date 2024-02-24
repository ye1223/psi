package com.roadjava.psi.bean.request.user;

import com.roadjava.psi.bean.request.BaseSearchReq;
import lombok.Data;

/**
 * @author zhaodaowen
 */
@Data
public class UserSearchReq extends BaseSearchReq {
    private String userName;
    private Long userId;
}
