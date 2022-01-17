package org.lecoder.easyflow.modules.core.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.lecoder.easyflow.modules.core.dto.NodeTaskDTO;
import org.lecoder.easyflow.modules.core.dto.NodeUserDTO;
import org.lecoder.easyflow.modules.core.entity.FlowDefinitionNode;
import org.lecoder.easyflow.modules.core.entity.FlowInstance;
import org.lecoder.easyflow.modules.core.entity.FlowInstanceNode;
import org.lecoder.easyflow.modules.core.enums.NodeStatusEnum;
import org.lecoder.easyflow.modules.core.mapper.FlowInstanceNodeMapper;
import org.lecoder.easyflow.modules.core.node.BaseNode;
import org.lecoder.easyflow.modules.core.service.IFlowInstanceNodeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 流程节点表 服务实现类
 * </p>
 *
 * @author lijile
 * @since 2021-10-25
 */
@Service
public class FlowInstanceNodeServiceImpl extends ServiceImpl<FlowInstanceNodeMapper, FlowInstanceNode> implements IFlowInstanceNodeService {

    @Override
    public int saveInstanceNode(FlowInstance flowInstance, int parentNodeId, FlowDefinitionNode definitionNode) {
        String relClass = definitionNode.getRelClass();
        BaseNode node = ReflectUtil.newInstance(relClass);
        NodeTaskDTO nodeTaskDTO = new NodeTaskDTO();
        BeanUtils.copyProperties(flowInstance, nodeTaskDTO);
        nodeTaskDTO.setNodeCode(definitionNode.getNodeCode());
        List<NodeUserDTO> nodeList = node.assign(nodeTaskDTO);

        List<FlowInstanceNode> instanceNodeList = new ArrayList<>(nodeList.size());
        int nodeId = parentNodeId + 1000;
        for (NodeUserDTO nodeUserDTO : nodeList) {
            FlowInstanceNode instanceNode = new FlowInstanceNode();
            instanceNode.setUsername(nodeUserDTO.getUsername());
            instanceNode.setFullname(nodeUserDTO.getFullname());
            instanceNode.setParentId(parentNodeId);
            instanceNode.setNodeId(nodeId);
            instanceNode.setTaskCode(RandomUtil.randomString(20));
            instanceNode.setNodeCode(definitionNode.getNodeCode());
            instanceNode.setInstanceCode(flowInstance.getInstanceCode());
            instanceNode.setNodeName(definitionNode.getNodeName());
            instanceNode.setActionStatus(NodeStatusEnum.WAIT.getStatus());
            instanceNodeList.add(instanceNode);
        }
        saveBatch(instanceNodeList, instanceNodeList.size());
        return nodeId;
    }

    @Override
    public FlowInstanceNode getByTaskCode(String taskCode) {
        LambdaQueryWrapper queryWrapper = new LambdaQueryWrapper<FlowInstanceNode>().eq(FlowInstanceNode::getTaskCode, taskCode);
        return getOne(queryWrapper);
    }
}
