package org.lecoder.easyflow.modules.core.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.lecoder.easyflow.modules.core.dto.DefinitionFormDTO;
import org.lecoder.easyflow.modules.core.entity.FlowDefinition;
import org.lecoder.easyflow.modules.core.entity.FlowDefinitionNode;
import org.lecoder.easyflow.modules.core.mapper.FlowDefinitionMapper;
import org.lecoder.easyflow.modules.core.mapper.FlowDefinitionNodeMapper;
import org.lecoder.easyflow.modules.core.service.IFlowDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 流程定义表 服务实现类
 * </p>
 *
 * @author lijile
 * @since 2021-10-25
 */
@Service
public class FlowDefinitionServiceImpl extends ServiceImpl<FlowDefinitionMapper, FlowDefinition> implements IFlowDefinitionService {

    @Autowired
    private FlowDefinitionNodeMapper flowDefinitionNodeMapper;

    @Override
    public FlowDefinition getByCode(String definitionCode) {
        LambdaQueryWrapper<FlowDefinition> definitionLambdaQueryWrapper = new LambdaQueryWrapper();
        definitionLambdaQueryWrapper.eq(FlowDefinition::getDefinitionCode, definitionCode);
        return getOne(definitionLambdaQueryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveDefinition(DefinitionFormDTO definitionForm) {
        FlowDefinition flowDefinition = new FlowDefinition();
        flowDefinition.setDefinitionName(definitionForm.getDefinitionName());
        flowDefinition.setDefinitionCode("def_" + RandomUtil.randomString(10));
        // 回显
        definitionForm.setDefinitionCode(flowDefinition.getDefinitionCode());
        return save(flowDefinition);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteDefinition(String definitionCode) {
        FlowDefinition flowDefinition = getByCode(definitionCode);
        LambdaQueryWrapper<FlowDefinitionNode> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FlowDefinitionNode::getDefinitionCode, flowDefinition.getDefinitionCode());
        flowDefinitionNodeMapper.delete(queryWrapper);
        return removeById(flowDefinition.getId());
    }
}
