package org.lecoder.easyflow.modules.core.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.lecoder.easyflow.common.toolkit.RequestHolder;
import org.lecoder.easyflow.modules.core.entity.FlowInstance;
import org.lecoder.easyflow.modules.core.enums.FlowModuleEnum;
import org.lecoder.easyflow.modules.core.mapper.FlowInstanceMapper;
import org.lecoder.easyflow.modules.core.service.IFlowInstanceService;
import org.lecoder.easyflow.modules.sys.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 流程实例表 服务实现类
 * </p>
 *
 * @author lijile
 * @since 2021-10-25
 */
@Service
public class FlowInstanceServiceImpl extends ServiceImpl<FlowInstanceMapper, FlowInstance> implements IFlowInstanceService {

    @Override
    public FlowInstance saveInstance(String definitionCode) {
        FlowInstance flowInstance = new FlowInstance();
        String instanceCode = RandomUtil.randomString(20);
        flowInstance.setInstanceCode(instanceCode);
        flowInstance.setDefinitionCode(definitionCode);
        flowInstanceMapper.insert(flowInstance);
        return flowInstanceMapper.selectOne(new LambdaQueryWrapper<FlowInstance>().eq(FlowInstance::getInstanceCode, instanceCode));
    }

    @Override
    public FlowInstance saveInstance(FlowModuleEnum flowModuleEnum, String definitionCode) {
        UserDTO currentUser = RequestHolder.getCurrentUser();
        FlowInstance flowInstance = new FlowInstance();
        String instanceCode = RandomUtil.randomString(20);
        flowInstance.setInstanceCode(instanceCode);
        flowInstance.setDefinitionCode(definitionCode);
        flowInstance.setUsername(currentUser.getUsername());
        flowInstance.setFullname(currentUser.getFullname());
        flowInstance.setModuleId(flowModuleEnum.getModuleId());
        flowInstance.setModuleName(flowModuleEnum.getModuleName());
        flowInstanceMapper.insert(flowInstance);
        return flowInstanceMapper.selectOne(new LambdaQueryWrapper<FlowInstance>().eq(FlowInstance::getInstanceCode, instanceCode));
    }

    @Autowired private FlowInstanceMapper flowInstanceMapper;
}
