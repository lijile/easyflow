package org.lecoder.easyflow.modules.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.lecoder.easyflow.modules.core.dto.TaskFilterDTO;
import org.lecoder.easyflow.modules.core.dto.TaskInstanceDTO;
import org.lecoder.easyflow.modules.core.entity.FlowInstance;
import org.lecoder.easyflow.modules.core.entity.FlowInstanceNode;
import org.lecoder.easyflow.modules.core.enums.FlowActionEnum;

import java.util.EnumSet;
import java.util.List;

/**
 * 流程接口
 *
 * @author: lijile
 * @date: 2021/11/2 11:43
 * @version: 1.0
 */
public interface IFlowService {
    /**
     * 计算流程操作权限
     * @author: lijile
     * @date: 2021/11/2 11:55
     * @param instance
     * @param nodeList
     * @return
     */
    EnumSet<FlowActionEnum> countActions(FlowInstance instance, List<FlowInstanceNode> nodeList);

    /**
     * 同意
     * @author: lijile
     * @date: 2021/11/2 16:34
     * @param taskCode
     * @param note
     * @return
     */
    void agree(String taskCode, String note);

    /**
     * 不同意
     * @author: lijile
     * @date: 2021/11/2 16:34
     * @param taskCode
     * @param note
     * @return
     */
    void disagree(String taskCode, String note);

    /**
     * 改派
     * @author: lijile
     * @date: 2021/11/3 15:16
     * @param taskCode
     * @param username
     * @return
     */
    void changeNode(String taskCode, String username);

    /**
     * 任务列表
     * @author: lijile
     * @date: 2021/11/4 16:36
     * @param page
     * @param queryFilter
     * @return
     */
    IPage<TaskInstanceDTO> listMyTask(Page<TaskInstanceDTO> page, TaskFilterDTO queryFilter);
}
