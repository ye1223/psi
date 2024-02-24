package com.roadjava.psi.ex;

/**
 * 业务异常
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public class BizEx extends RuntimeException {

    public BizEx(String msg) {
        super(msg);
    }
}
