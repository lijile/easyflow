package org.lecoder.easyflow.modules.core.service;

import org.lecoder.easyflow.modules.core.entity.FlowDefinitionNode;
import org.lecoder.easyflow.modules.core.entity.FlowInstance;
import org.lecoder.easyflow.modules.core.entity.FlowInstanceNode;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 流程节点表 服务类
 * </p>
 *
 * @author lijile
 * @since 2021-10-25
 */
public interface IFlowInstanceNodeService extends IService<FlowInstanceNode> {

    /**
     * 保存实例节点
     * @author: lijile
     * @date: 2021/10/25 16:10
     * @param flowInstance
     * @param parentNodeId
     * @param definitionNode
     * @return
     */
    int saveInstanceNode(FlowInstance flowInstance, int parentNodeId, FlowDefinitionNode definitionNode);

    /**
     * 根据任务编码查询节点
     * @author: lijile
     * @date: 2021/11/3 14:37
     * @param taskCode
     * @return
     */
    FlowInstanceNode getByTaskCode(String taskCode);
}
