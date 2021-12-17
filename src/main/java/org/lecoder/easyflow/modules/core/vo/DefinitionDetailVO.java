package org.lecoder.easyflow.modules.core.vo;

import lombok.Data;
import org.lecoder.easyflow.modules.core.entity.FlowDefinition;
import org.lecoder.easyflow.modules.core.entity.FlowDefinitionNode;

import java.util.List;

/**
 * 流程定义详情
 *
 * @author lijile
 * @date 2021/12/16 17:32
 */
@Data
public class DefinitionDetailVO {

    private FlowDefinition definition;

    private List<FlowDefinitionNode> nodeList;

}
