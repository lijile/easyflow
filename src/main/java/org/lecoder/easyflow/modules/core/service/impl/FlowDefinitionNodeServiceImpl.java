package org.lecoder.easyflow.modules.core.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.lecoder.easyflow.modules.core.dto.DefinitionNodeFormDTO;
import org.lecoder.easyflow.modules.core.entity.FlowDefinitionNode;
import org.lecoder.easyflow.modules.core.entity.FlowVariable;
import org.lecoder.easyflow.modules.core.mapper.FlowDefinitionNodeMapper;
import org.lecoder.easyflow.modules.core.service.IFlowDefinitionNodeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.util.List;

/**
 * <p>
 * 流程定义节点表 服务实现类
 * </p>
 *
 * @author lijile
 * @since 2021-10-25
 */
@Service
public class FlowDefinitionNodeServiceImpl extends ServiceImpl<FlowDefinitionNodeMapper, FlowDefinitionNode> implements IFlowDefinitionNodeService {

    @Override
    public FlowDefinitionNode getNextDefinitionCode(String definitionCode, String parentCode, List<FlowVariable> variableList) {
        LambdaQueryWrapper<FlowDefinitionNode> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FlowDefinitionNode::getDefinitionCode, definitionCode);
        if (parentCode == null) {
            queryWrapper.isNull(FlowDefinitionNode::getParentCode);
        } else {
            queryWrapper.eq(FlowDefinitionNode::getParentCode, parentCode);
        }
        queryWrapper.orderByDesc(FlowDefinitionNode::getPriority);
        List<FlowDefinitionNode> definitionNodeList = flowDefinitionNodeMapper.selectList(queryWrapper);
        for (FlowDefinitionNode definitionNode : definitionNodeList) {
            if (StrUtil.isEmpty(definitionNode.getConditionScript())) {
                return definitionNode;
            }
            if (checkCondition(definitionNode.getConditionScript(), variableList)) {
                return definitionNode;
            }
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveDefinitionNode(DefinitionNodeFormDTO definitionNodeForm) {
        FlowDefinitionNode flowDefinitionNode = new FlowDefinitionNode();
        BeanUtils.copyProperties(definitionNodeForm, flowDefinitionNode);
        String definitionNodeCode = "node_" + RandomUtil.randomString(10);
        flowDefinitionNode.setNodeCode(definitionNodeCode);
        definitionNodeForm.setNodeCode(definitionNodeCode);
        return save(flowDefinitionNode);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateDefinitionNode(DefinitionNodeFormDTO definitionNodeForm) {
        FlowDefinitionNode flowDefinitionNode = getByCode(definitionNodeForm.getDefinitionCode(), definitionNodeForm.getNodeCode());
        BeanUtils.copyProperties(definitionNodeForm, flowDefinitionNode);
        return updateById(flowDefinitionNode);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteDefinitionNode(String definitionCode, String nodeCode) {
        FlowDefinitionNode flowDefinitionNode = getByCode(definitionCode, nodeCode);
        LambdaQueryWrapper<FlowDefinitionNode> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FlowDefinitionNode::getDefinitionCode, flowDefinitionNode.getDefinitionCode());
        queryWrapper.eq(FlowDefinitionNode::getParentCode, flowDefinitionNode.getNodeCode());

        // 更新子节点
        LambdaUpdateWrapper<FlowDefinitionNode> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(FlowDefinitionNode::getParentCode, flowDefinitionNode.getNodeCode())
                .set(FlowDefinitionNode::getParentCode, flowDefinitionNode.getParentCode());
        flowDefinitionNodeMapper.update(null, lambdaUpdateWrapper);

        return removeById(flowDefinitionNode.getId());
    }

    @Override
    public FlowDefinitionNode getByCode(String definitionCode, String nodeCode) {
        LambdaQueryWrapper<FlowDefinitionNode> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FlowDefinitionNode::getDefinitionCode, definitionCode);
        queryWrapper.eq(FlowDefinitionNode::getNodeCode, nodeCode);
        FlowDefinitionNode flowDefinitionNode = flowDefinitionNodeMapper.selectOne(queryWrapper);
        return flowDefinitionNode;
    }

    private boolean checkCondition(String conditionScript, List<FlowVariable> variableList) {
        Bindings bindings = scriptEngine.createBindings();
        for (FlowVariable variable : variableList) {
            bindings.put(variable.getKeyy(), variable.getValuee());
        }
        try {
            Object result = scriptEngine.eval(conditionScript, bindings);
            if (result instanceof Boolean) {
                return (Boolean) result;
            }
            return false;
        } catch (ScriptException e) {
            return false;
        }
    }

    @Autowired private FlowDefinitionNodeMapper flowDefinitionNodeMapper;

    @Autowired private ScriptEngine scriptEngine;
}
