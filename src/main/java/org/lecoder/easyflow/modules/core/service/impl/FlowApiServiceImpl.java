package org.lecoder.easyflow.modules.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.lecoder.easyflow.common.exception.FlowException;
import org.lecoder.easyflow.modules.core.dto.NodeUserDTO;
import org.lecoder.easyflow.modules.core.entity.*;
import org.lecoder.easyflow.modules.core.enums.FlowModuleEnum;
import org.lecoder.easyflow.modules.core.enums.InstanceStatusEnum;
import org.lecoder.easyflow.modules.core.enums.NodeStatusEnum;
import org.lecoder.easyflow.modules.core.service.*;
import org.lecoder.easyflow.modules.core.toolkit.VariableTypeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 流程接口服务实现
 *
 * @author: lijile
 * @date: 2021/10/25 14:22
 * @version: 1.0
 */
@Service
public class FlowApiServiceImpl implements IFlowApiService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String start(FlowModuleEnum flowModuleEnum, String definitionCode, Map<String, Object> variables) {
        // 检查流程定义
        FlowDefinition flowDefinition = flowDefinitionService.getOne(new LambdaQueryWrapper<FlowDefinition>().eq(FlowDefinition::getDefinitionCode, definitionCode));
        if (flowDefinition == null) {
            throw new FlowException("流程定义不存在");
        }

        // 生成流程实例
        FlowInstance flowInstance = flowInstanceService.saveInstance(flowModuleEnum, definitionCode);

        // 保存流程实例变量
        List<FlowVariable> variableList = VariableTypeHelper.getVariables(variables);
        variableList.stream().forEach(variable -> variable.setInstanceCode(flowInstance.getInstanceCode()));
        flowVariableService.saveBatch(variableList);

        // 获取下一个节点定义
        FlowDefinitionNode flowDefinitionNode = flowDefinitionNodeService.getNextDefinitionCode(definitionCode, null, variableList);

        // 生成流程节点
        flowInstanceNodeService.saveInstanceNode(flowInstance, 0, flowDefinitionNode);

        return flowInstance.getInstanceCode();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public FlowInstance agree(String taskCode, String note) {
        // 检查当前节点
        FlowInstanceNode instanceNode = getWaitNode(taskCode);

        // 检查流程实例
        FlowInstance instance = getInProgressInstance(instanceNode.getInstanceCode());

        // 更新当前节点
        instanceNode.setActionStatus(NodeStatusEnum.AGREE.getStatus());
        instanceNode.setActionTime(LocalDateTime.now());
        instanceNode.setNote(note);
        flowInstanceNodeService.updateById(instanceNode);

        List<FlowVariable> variableList = flowVariableService.list(new LambdaQueryWrapper<FlowVariable>().eq(FlowVariable::getInstanceCode, instance.getInstanceCode()));
        FlowDefinitionNode definitionNode = flowDefinitionNodeService.getNextDefinitionCode(instance.getDefinitionCode(), instanceNode.getNodeCode(), variableList);
        if (definitionNode == null) {
            // 流程结束
            instance.setStatus(InstanceStatusEnum.FINISHED.getStatus());
            flowInstanceService.updateById(instance);
            return instance;
        }
        // 生成下一节点实例
        flowInstanceNodeService.saveInstanceNode(instance, instanceNode.getNodeId(), definitionNode);
        return instance;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public FlowInstance disagree(String taskCode, String note) {
        // 检查当前节点
        FlowInstanceNode instanceNode = getWaitNode(taskCode);

        // 检查流程实例
        FlowInstance instance = getInProgressInstance(instanceNode.getInstanceCode());

        instance.setStatus(InstanceStatusEnum.TERMINATED.getStatus());
        flowInstanceService.updateById(instance);

        // 更新当前节点
        instanceNode.setActionStatus(NodeStatusEnum.DISAGREE.getStatus());
        instanceNode.setActionTime(LocalDateTime.now());
        instanceNode.setNote(note);
        flowInstanceNodeService.updateById(instanceNode);
        return instance;
    }

    @Override
    public void changeNode(String taskCode, NodeUserDTO nodeUser) {
        // 检查当前节点
        FlowInstanceNode instanceNode = getWaitNode(taskCode);

        instanceNode.setUsername(nodeUser.getUsername());
        instanceNode.setFullname(nodeUser.getFullname());
        flowInstanceNodeService.updateById(instanceNode);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<FlowInstanceNode> preview(String definitionCode, Map<String, Object> variables) {
        // 检查流程定义
        FlowDefinition flowDefinition = flowDefinitionService.getOne(new LambdaQueryWrapper<FlowDefinition>().eq(FlowDefinition::getDefinitionCode, definitionCode));
        if (flowDefinition == null) {
            throw new FlowException("流程定义不存在");
        }

        // 生成流程实例
        FlowInstance flowInstance = flowInstanceService.saveInstance(definitionCode);

        int parentNodeId = 0;
        FlowDefinitionNode flowDefinitionNode = null;
        while (parentNodeId >= 0) {
            // 获取下一个节点定义
            List<FlowVariable> variableList = VariableTypeHelper.getVariables(variables);
            flowDefinitionNode = flowDefinitionNodeService.getNextDefinitionCode(definitionCode, flowDefinitionNode == null ? null : flowDefinitionNode.getNodeCode(), variableList);
            if (flowDefinitionNode == null) {
                break;
            }

            // 生成流程节点
            parentNodeId = flowInstanceNodeService.saveInstanceNode(flowInstance, parentNodeId, flowDefinitionNode);
        }
        return flowInstanceNodeService.list(new LambdaQueryWrapper<FlowInstanceNode>().eq(FlowInstanceNode::getInstanceCode, flowInstance.getInstanceCode()));
    }

    private FlowInstanceNode getWaitNode(String taskCode) {
        FlowInstanceNode instanceNode = flowInstanceNodeService.getByTaskCode(taskCode);
        if (instanceNode == null || !NodeStatusEnum.canAgree(instanceNode.getActionStatus())) {
            throw new FlowException("任务不存在");
        }
        return instanceNode;
    }

    private FlowInstance getInProgressInstance(String instanceCode) {
        FlowInstance instance = flowInstanceService.getOne(new LambdaQueryWrapper<FlowInstance>().eq(FlowInstance::getInstanceCode, instanceCode));
        if (instance == null || !InstanceStatusEnum.inProgress(instance.getStatus())) {
            throw new FlowException("流程不存在");
        }
        return instance;
    }

    @Autowired private IFlowDefinitionService flowDefinitionService;

    @Autowired private IFlowDefinitionNodeService flowDefinitionNodeService;

    @Autowired private IFlowInstanceService flowInstanceService;

    @Autowired private IFlowInstanceNodeService flowInstanceNodeService;

    @Autowired private IFlowVariableService flowVariableService;
}
