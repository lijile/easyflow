package org.lecoder.easyflow.common.exception;

/**
 * 常规流程异常
 *
 * @author: lijile
 * @date: 2021/10/25 13:44
 * @version: 1.0
 */
public class FlowException extends RuntimeException {
    public FlowException(String errMsg) {
        super(errMsg);
    }
}
