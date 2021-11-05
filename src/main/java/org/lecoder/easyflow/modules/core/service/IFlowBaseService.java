package org.lecoder.easyflow.modules.core.service;

/**
 * 流程基本服务
 *
 * @author: lijile
 * @date: 2021/10/27 12:34
 * @version: 1.0
 */
public interface IFlowBaseService {
    /**
     * 流程结束后调用
     * @author: lijile
     * @date: 2021/10/27 12:41
     * @param instanceCode
     * @return
     */
    default void flowFinished(String instanceCode) {

    }

    /**
     * 流程终止后调用，回退某些业务操作，如剩余假期还原
     * @author: lijile
     * @date: 2021/11/2 17:07
     * @param instanceCode
     * @return
     */
    default void flowTerminated(String instanceCode) {

    }

    /**
     * 查询表单详细时调用
     * 需要返回展示给审批人审阅的表单信息
     * @author: lijile
     * @date: 2021/10/27 15:32
     * @param instanceCode
     * @return
     */
    default Object getApplyForm(String instanceCode) {
        return null;
    }
}
