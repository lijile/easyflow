package org.lecoder.easyflow.modules.core.entity;

import org.lecoder.easyflow.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 流程定义表
 * </p>
 *
 * @author lijile
 * @since 2021-10-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FlowDefinition extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 定义代码
     */
    private String definitionCode;

    /**
     * 流程定义名称
     */
    private String definitionName;


}
