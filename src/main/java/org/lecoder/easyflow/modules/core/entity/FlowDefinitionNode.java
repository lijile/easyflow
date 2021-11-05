package org.lecoder.easyflow.modules.core.entity;

import org.lecoder.easyflow.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 流程定义节点表
 * </p>
 *
 * @author lijile
 * @since 2021-10-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FlowDefinitionNode extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 定义代码
     */
    private String definitionCode;

    /**
     * 父节点代码
     */
    private String parentCode;

    /**
     * 节点代码
     */
    private String nodeCode;

    /**
     * 节点名称
     */
    private String nodeName;

    /**
     * 条件脚本
     */
    private String conditionScript;

    /**
     * 关联类的全路径
     */
    private String relClass;

    /**
     * 优先级
     */
    private Integer priority;


}
