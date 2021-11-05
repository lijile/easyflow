package org.lecoder.easyflow.modules.core.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.lecoder.easyflow.modules.core.entity.FlowDefinitionNode;
import org.lecoder.easyflow.modules.core.entity.FlowVariable;
import org.lecoder.easyflow.modules.core.mapper.FlowDefinitionNodeMapper;
import org.lecoder.easyflow.modules.core.service.IFlowDefinitionNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("definition_code", definitionCode);
        if (parentCode == null) {
            queryWrapper.isNull("parent_code");
        } else {
            queryWrapper.eq("parent_code", parentCode);
        }
        queryWrapper.orderByDesc("priority");
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
