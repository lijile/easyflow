package org.lecoder.easyflow.modules.core.service.impl;

import org.lecoder.easyflow.modules.core.entity.FlowDefinition;
import org.lecoder.easyflow.modules.core.mapper.FlowDefinitionMapper;
import org.lecoder.easyflow.modules.core.service.IFlowDefinitionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
