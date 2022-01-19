package org.lecoder.easyflow.modules.core.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 流程节点表单
 *
 * @author lijile
 * @date 2022/1/17 14:27
 */
@Data
public class DefinitionNodeFormDTO {

    /**
     * 定义代码
     */
    @NotEmpty(message = "流程定义代码不为空")
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
    @NotEmpty(message = "节点名称不为空")
    private String nodeName;

    /**
     * 条件脚本
     */
    private String conditionScript;

    /**
     * 关联类的全路径
     */
    @NotEmpty(message = "关联类路径不为空")
    private String relClass;

    /**
     * 优先级
     */
    private Integer priority;

}
