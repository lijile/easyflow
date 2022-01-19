package org.lecoder.easyflow.modules.core.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 流程定义表单
 *
 * @author lijile
 * @date 2022/1/17 14:16
 */
@Data
public class DefinitionFormDTO {

    /**
     * 定义代码
     */
    private String definitionCode;

    /**
     * 流程定义名称
     */
    @NotBlank(message = "流程定义名称不为空")
    private String definitionName;

}
