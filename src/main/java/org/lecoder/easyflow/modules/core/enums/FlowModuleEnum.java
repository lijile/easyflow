package org.lecoder.easyflow.modules.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.lecoder.easyflow.modules.core.service.IFlowBaseService;
import org.lecoder.easyflow.modules.leave.service.ILeaveService;

/**
 * 流程模块
 *
 * @author: lijile
 * @date: 2021/10/27 11:57
 * @version: 1.0
 */
@Getter
@AllArgsConstructor
public enum  FlowModuleEnum {

    LEAVE("leave", "请假", ILeaveService.class),
    ;

    public static FlowModuleEnum getById(String moduleId) {
        for (FlowModuleEnum moduleEnum : values()) {
            if (moduleEnum.getModuleId().equals(moduleId)) {
                return moduleEnum;
            }
        }
        return null;
    }

    private String moduleId;

    private String moduleName;

    private Class<? extends IFlowBaseService> serviceClass;

}
