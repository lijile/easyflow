package org.lecoder.easyflow.modules.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.lecoder.easyflow.common.exception.FlowException;
import org.lecoder.easyflow.common.toolkit.RequestHolder;
import org.lecoder.easyflow.common.toolkit.SpringContextHolder;
import org.lecoder.easyflow.modules.core.dto.NodeUserDTO;
import org.lecoder.easyflow.modules.core.dto.TaskFilterDTO;
import org.lecoder.easyflow.modules.core.dto.TaskInstanceDTO;
import org.lecoder.easyflow.modules.core.entity.FlowInstance;
import org.lecoder.easyflow.modules.core.entity.FlowInstanceNode;
import org.lecoder.easyflow.modules.core.enums.FlowActionEnum;
import org.lecoder.easyflow.modules.core.enums.FlowModuleEnum;
import org.lecoder.easyflow.modules.core.enums.InstanceStatusEnum;
import org.lecoder.easyflow.modules.core.enums.NodeStatusEnum;
import org.lecoder.easyflow.modules.core.mapper.FlowInstanceMapper;
import org.lecoder.easyflow.modules.core.service.IFlowApiService;
import org.lecoder.easyflow.modules.core.service.IFlowBaseService;
import org.lecoder.easyflow.modules.core.service.IFlowInstanceNodeService;
import org.lecoder.easyflow.modules.core.service.IFlowService;
import org.lecoder.easyflow.modules.sys.dto.UserDTO;
import org.lecoder.easyflow.modules.sys.entity.SysUser;
import org.lecoder.easyflow.modules.sys.service.ISysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.List;

/**
 * 流程接口实现
 *
 * @author: lijile
 * @date: 2021/11/2 11:43
 * @version: 1.0
 */
@Service
public class FlowServiceImpl implements IFlowService {
    @Override
    public EnumSet<FlowActionEnum> countActions(FlowInstance instance, List<FlowInstanceNode> nodeList) {
        EnumSet actions = EnumSet.noneOf(FlowActionEnum.class);

        boolean hasApproveNode = nodeList.stream().anyMatch(this::canApprove);
        // 审批|改派
        if (hasApproveNode) {
            actions.add(FlowActionEnum.AGREE);
            actions.add(FlowActionEnum.DISAGREE);
            actions.add(FlowActionEnum.CHANGE_NODE);
        }
        return actions;
    }

    @Override
    public void agree(String taskCode, String note) {
        FlowInstanceNode node = instanceNodeService.getByTaskCode(taskCode);
        if (!canApprove(node)) {
            throw new FlowException("不支持该操作");
        }
        FlowInstance instance = flowApiService.agree(node.getTaskCode(), note);
        if (instance.getStatus() == InstanceStatusEnum.FINISHED.getStatus()) {
            // 通知对应模块，流程结束
            FlowModuleEnum flowModuleEnum = FlowModuleEnum.getById(instance.getModuleId());
            IFlowBaseService flowBaseService = SpringContextHolder.getBean(flowModuleEnum.getServiceClass());
            flowBaseService.flowFinished(instance.getInstanceCode());
        }
    }

    @Override
    public void disagree(String taskCode, String note) {
        FlowInstanceNode node = instanceNodeService.getByTaskCode(taskCode);
        if (!canApprove(node)) {
            throw new FlowException("不支持该操作");
        }
        FlowInstance instance = flowApiService.disagree(node.getTaskCode(), note);
        // 通知对应模块，流程终止
        FlowModuleEnum flowModuleEnum = FlowModuleEnum.getById(instance.getModuleId());
        IFlowBaseService flowBaseService = SpringContextHolder.getBean(flowModuleEnum.getServiceClass());
        flowBaseService.flowTerminated(instance.getInstanceCode());
    }

    @Override
    public void changeNode(String taskCode, String username) {
        FlowInstanceNode node = instanceNodeService.getByTaskCode(taskCode);
        if (!canApprove(node)) {
            throw new FlowException("不支持该操作");
        }
        SysUser targetUser = userService.getOne(new QueryWrapper<SysUser>().eq("username", username));
        NodeUserDTO nodeUser = new NodeUserDTO();
        BeanUtils.copyProperties(targetUser, nodeUser);
        flowApiService.changeNode(taskCode, nodeUser);
    }

    @Override
    public IPage<TaskInstanceDTO> listMyTask(Page<TaskInstanceDTO> page, TaskFilterDTO queryFilter) {
        UserDTO currentUser = RequestHolder.getCurrentUser();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("t2.username", currentUser.getUsername());
        if (queryFilter.getModuleId() != null) {
            queryWrapper.eq("t1.module_id", queryFilter.getModuleId());
        }
        if (queryFilter.getStartDate() != null) {
            queryWrapper.ge("t2.apply_time", queryFilter.getStartDate());
        }
        if (queryFilter.getEndDate() != null) {
            queryWrapper.le("t2.apply_time", queryFilter.getEndDate());
        }
        return instanceMapper.taskPage(page, queryWrapper);
    }

    private boolean canApprove(FlowInstanceNode node) {
        UserDTO currentUser = RequestHolder.getCurrentUser();
        return node != null
                && node.getUsername().equals(currentUser.getUsername())
                && NodeStatusEnum.canAgree(node.getActionStatus());
    }

    @Autowired private IFlowApiService flowApiService;

    @Autowired private IFlowInstanceNodeService instanceNodeService;

    @Autowired private ISysUserService userService;

    @Autowired private FlowInstanceMapper instanceMapper;

}
