package org.lecoder.easyflow.modules.core.service;

import org.lecoder.easyflow.modules.core.dto.DefinitionFormDTO;
import org.lecoder.easyflow.modules.core.entity.FlowDefinition;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 流程定义表 服务类
 * </p>
 *
 * @author lijile
 * @since 2021-10-25
 */
public interface IFlowDefinitionService extends IService<FlowDefinition> {

    /**
     * 根据流程代码返回实例
     * @author lijile
     * @date 2022/1/17 14:54
     * @param definitionCode
     * @return
     */
    FlowDefinition getByCode(String definitionCode);

    /**
     * 保存流程定义
     * @author lijile
     * @date 2022/1/17 14:52
     * @param definitionForm
     * @return
     */
    boolean saveDefinition(DefinitionFormDTO definitionForm);

    /**
     * 删除流程定义
     * @author lijile
     * @date 2022/1/18 14:35
     * @param definitionCode
     * @return
     */
    boolean deleteDefinition(String definitionCode);
}
