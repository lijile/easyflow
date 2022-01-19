package org.lecoder.easyflow.modules.core.service;

import org.lecoder.easyflow.modules.core.dto.DefinitionNodeFormDTO;
import org.lecoder.easyflow.modules.core.entity.FlowDefinitionNode;
import com.baomidou.mybatisplus.extension.service.IService;
import org.lecoder.easyflow.modules.core.entity.FlowVariable;

import java.util.List;

/**
 * <p>
 * 流程定义节点表 服务类
 * </p>
 *
 * @author lijile
 * @since 2021-10-26
 */
public interface IFlowDefinitionNodeService extends IService<FlowDefinitionNode> {
    /**
     * 获取下一个流程定义节点
     * @author: lijile
     * @date: 2021/10/25 17:40
     * @param definitionCode 流程定义编号
     * @param parentCode 父节点
     * @param variables
     * @return
     */
    FlowDefinitionNode getNextDefinitionCode(String definitionCode, String parentCode, List<FlowVariable> variables);

    /**
     * 创建流程节点
     * @author lijile
     * @date 2022/1/17 14:34
     * @param definitionNodeForm
     * @return
     */
    boolean saveDefinitionNode(DefinitionNodeFormDTO definitionNodeForm);

    /**
     * 更新流程节点
     * @author lijile
     * @date 2022/1/18 10:38
     * @param definitionNodeForm
     * @return
     */
    boolean updateDefinitionNode(DefinitionNodeFormDTO definitionNodeForm);

    /**
     * 删除节点
     * @author lijile
     * @date 2022/1/18 14:36
     * @param definitionCode
     * @param nodeCode
     * @return
     */
    boolean deleteDefinitionNode(String definitionCode, String nodeCode);

    /**
     * 获取流程节点
     * @author lijile
     * @date 2022/1/18 18:40
     * @param definitionCode
     * @param nodeCode
     * @return
     */
    FlowDefinitionNode getByCode(String definitionCode, String nodeCode);
}
