package org.lecoder.easyflow.modules.core.service;

import org.lecoder.easyflow.modules.core.entity.FlowDefinitionNode;
import com.baomidou.mybatisplus.extension.service.IService;
import org.lecoder.easyflow.modules.core.entity.FlowVariable;

import java.util.List;

/**
 * <p>
 * 流程定义节点表 服务类
 * </p>
 *
 * @author lijile
 * @since 2021-10-26
 */
public interface IFlowDefinitionNodeService extends IService<FlowDefinitionNode> {
    /**
     * 获取下一个流程定义节点
     * @author: lijile
     * @date: 2021/10/25 17:40
     * @param definitionCode 流程定义编号
     * @param parentCode 父节点
     * @param variables
     * @return
     */
    FlowDefinitionNode getNextDefinitionCode(String definitionCode, String parentCode, List<FlowVariable> variables);
}
