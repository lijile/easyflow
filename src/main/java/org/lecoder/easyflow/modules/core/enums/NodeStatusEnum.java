package org.lecoder.easyflow.modules.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 流程节点状态
 *
 * @author: lijile
 * @date: 2021/10/25 17:11
 * @version: 1.0
 */
@Getter
@AllArgsConstructor
public enum NodeStatusEnum {

    WAIT(0, "待处理"),
    AGREE(1, "同意"),
    DISAGREE(2, "不同意"),
    ;

    int status;

    String name;

    public static boolean canAgree(int status) {
        return status == WAIT.status;
    }

}
