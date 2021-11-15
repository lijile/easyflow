package org.lecoder.easyflow.modules.leave.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.lecoder.easyflow.modules.leave.handler.AnnualLeaveHandler;
import org.lecoder.easyflow.modules.leave.handler.ILeaveTypeHandler;
import org.lecoder.easyflow.modules.leave.handler.MaternityLeaveHandler;

/**
 * 请假类型
 *
 * @author: lijile
 * @date: 2021/11/15 15:59
 * @version: 1.0
 */
@Getter
@AllArgsConstructor
public enum  LeaveTypeEnum {

    ANNUAL("年假", AnnualLeaveHandler.class),
    MATERINITY("产假",MaternityLeaveHandler.class),
    ;

    public static LeaveTypeEnum get(String name) {
        for (LeaveTypeEnum leaveTypeEnum : values()) {
            if (leaveTypeEnum.name.equals(name)) {
                return leaveTypeEnum;
            }
        }
        return null;
    }

    private String name;
    /**
     * 处理工具类
     */
    private Class<? extends ILeaveTypeHandler> handlerClass;

}
