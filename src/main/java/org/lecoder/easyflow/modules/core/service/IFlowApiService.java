package org.lecoder.easyflow.modules.core.service;

import org.lecoder.easyflow.modules.core.dto.NodeUserDTO;
import org.lecoder.easyflow.modules.core.entity.FlowInstance;
import org.lecoder.easyflow.modules.core.entity.FlowInstanceNode;
import org.lecoder.easyflow.modules.core.enums.FlowModuleEnum;

import java.util.List;
import java.util.Map;

/**
 * 流程接口
 *
 * @author: lijile
 * @date: 2021/10/25 14:21
 * @version: 1.0
 */
public interface IFlowApiService {
    /**
     * 开启流程
     * @author: lijile
     * @date: 2021/10/25 14:31
     * @param flowModuleEnum 模块
     * @param definitionCode 流程定义编号
     * @param variables 流程实例变量
     * @return 流程实例编号
     */
    String start(FlowModuleEnum flowModuleEnum, String definitionCode, Map<String, Object> variables);

    /**
     * 同意通过
     * @author: lijile
     * @date: 2021/10/25 17:03
     * @param taskCode
     * @param note
     * @return
     */
    FlowInstance agree(String taskCode, String note);

    /**
     * 不同意终止
     * @author: lijile
     * @date: 2021/11/2 16:53
     * @param taskCode
     * @return
     */
    FlowInstance disagree(String taskCode, String note);

    /**
     * 改签/改派
     * @author: lijile
     * @date: 2021/11/3 14:13
     * @param taskCode
     * @param nodeUser
     * @return
     */
    void changeNode(String taskCode, NodeUserDTO nodeUser);

    /**
     * 预览流程
     * @author: lijile
     * @date: 2021/10/26 11:24
     * @param definitionCode
     * @param variables
     * @return
     */
    List<FlowInstanceNode> preview(String definitionCode, Map<String, Object> variables);
}
