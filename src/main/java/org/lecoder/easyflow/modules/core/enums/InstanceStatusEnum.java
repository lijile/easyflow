package org.lecoder.easyflow.modules.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 流程实例状态
 *
 * @author: lijile
 * @date: 2021/10/25 17:11
 * @version: 1.0
 */
@Getter
@AllArgsConstructor
public enum InstanceStatusEnum {

    IN_PROGRESS(0, "进行中"),
    FINISHED(1, "已完成"),
    TERMINATED(2, "已终止"),
    ;

    int status;

    String name;

    public static boolean inProgress(int status) {
        return status == IN_PROGRESS.status;
    }

}
