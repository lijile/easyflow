package org.lecoder.easyflow.modules.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.lecoder.easyflow.modules.core.entity.FlowInstance;
import org.lecoder.easyflow.modules.core.enums.FlowModuleEnum;

/**
 * <p>
 * 流程实例表 服务类
 * </p>
 *
 * @author lijile
 * @since 2021-10-25
 */
public interface IFlowInstanceService extends IService<FlowInstance> {

    /**
     * 根据流程定义编码生成流程实例
     * @author: lijile
     * @date: 2021/10/25 16:09
     * @param definitionCode
     * @return
     */
    FlowInstance saveInstance(String definitionCode);

    /**
     * 根据流程定义编码生成流程实例
     * @author: lijile
     * @date: 2021/10/25 16:09
     * @param flowModuleEnum
     * @param definitionCode
     * @return
     */
    FlowInstance saveInstance(FlowModuleEnum flowModuleEnum, String definitionCode);

}
