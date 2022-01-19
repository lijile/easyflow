package org.lecoder.easyflow.modules.core.vo;

import lombok.Data;

/**
 * 节点关联类（位于org.lecoder.easyflow.modules.core.node）
 *
 * @author lijile
 * @date 2022/1/19 15:05
 */
@Data
public class NodeRelClassVO {

    /**
     * 类名
     */
    private String simpleName;

    /**
     * 类全路径
     */
    private String name;

    /**
     * 备注说明
     */
    private String note;

}
