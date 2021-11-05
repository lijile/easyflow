package org.lecoder.easyflow.modules.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.lecoder.easyflow.modules.core.entity.FlowVariable;
import org.lecoder.easyflow.modules.core.mapper.FlowVariableMapper;
import org.lecoder.easyflow.modules.core.service.IFlowVariableService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 流程实例变量 服务实现类
 * </p>
 *
 * @author lijile
 * @since 2021-10-25
 */
@Service
public class FlowVariableServiceImpl extends ServiceImpl<FlowVariableMapper, FlowVariable> implements IFlowVariableService {

}
