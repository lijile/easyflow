package org.lecoder.easyflow.modules.leave.handler;

import org.lecoder.easyflow.modules.leave.dto.LeaveFormDTO;
import org.lecoder.easyflow.modules.leave.entity.LeaveApply;

/**
 * 请假类型处理器
 *
 * @author: lijile
 * @date: 2021/11/15 15:50
 * @version: 1.0
 */
public interface ILeaveTypeHandler {
    /**
     * 提交前做的准备，如，检查请假天数是否合理
     * @author: lijile
     * @date: 2021/11/15 15:56
     * @return
     */
    default void beforeSubmit(String username, LeaveFormDTO leaveForm) {}

    /**
     * 退回以后执行的操作，如恢复剩余年假天数
     * @author: lijile
     * @date: 2021/11/15 15:55
     * @return
     */
    default void afterRollback(String username, LeaveApply leaveApply) {}
}
