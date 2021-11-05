package org.lecoder.easyflow.modules.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 流程操作
 *
 * @author: lijile
 * @date: 2021/11/2 11:39
 * @version: 1.0
 */
@Getter
@AllArgsConstructor
public enum FlowActionEnum {

    AGREE("同意"),
    DISAGREE("不同意"),
    CHANGE_NODE("改签"),
    ;

    private String note;
}
