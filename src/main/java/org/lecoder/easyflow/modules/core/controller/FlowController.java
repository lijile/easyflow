package org.lecoder.easyflow.modules.core.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.lecoder.easyflow.common.toolkit.SpringContextHolder;
import org.lecoder.easyflow.common.web.CommonResult;
import org.lecoder.easyflow.modules.core.dto.ApproveFormDTO;
import org.lecoder.easyflow.modules.core.dto.TaskFilterDTO;
import org.lecoder.easyflow.modules.core.dto.TaskInstanceDTO;
import org.lecoder.easyflow.modules.core.entity.FlowInstance;
import org.lecoder.easyflow.modules.core.entity.FlowInstanceNode;
import org.lecoder.easyflow.modules.core.enums.FlowActionEnum;
import org.lecoder.easyflow.modules.core.enums.FlowModuleEnum;
import org.lecoder.easyflow.modules.core.enums.NodeStatusEnum;
import org.lecoder.easyflow.modules.core.service.IFlowBaseService;
import org.lecoder.easyflow.modules.core.service.IFlowInstanceNodeService;
import org.lecoder.easyflow.modules.core.service.IFlowInstanceService;
import org.lecoder.easyflow.modules.core.service.IFlowService;
import org.lecoder.easyflow.modules.core.vo.FlowDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.EnumSet;
import java.util.List;

/**
 * 流程控制器
 *
 * @author: lijile
 * @date: 2021/10/27 14:16
 * @version: 1.0
 */
@Api(tags = "core.FlowController")
@RestController
@RequestMapping("/core/flow")
public class FlowController {

    /**
     * 我的任务
     * @author: lijile
     * @date: 2021/11/4 11:32
     * @return
     */
    @ApiOperation("我的任务")
    @GetMapping("/my-task")
    public CommonResult myTask(TaskFilterDTO queryFilter,
                               @RequestParam(defaultValue = "1") Integer pageNo,
                               @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<TaskInstanceDTO> page = new Page<>(pageNo, pageSize);
        IPage<TaskInstanceDTO> pageList = flowService.listMyTask(page, queryFilter);
        return CommonResult.success(pageList);
    }

    /**
     * 流程详情
     * @author: lijile
     * @date: 2021/10/27 15:18
     * @param instanceCode
     * @return
     */
    @ApiOperation("流程详情")
    @GetMapping("/detail")
    public CommonResult detail(String instanceCode) {
        FlowDetailVO flowDetailVO = new FlowDetailVO();
        FlowInstance instance = instanceService.getOne(new LambdaQueryWrapper<FlowInstance>().eq(FlowInstance::getInstanceCode, instanceCode));
        flowDetailVO.setInstance(instance);

        List<FlowInstanceNode> nodeList = instanceNodeService.list(new LambdaQueryWrapper<FlowInstanceNode>().eq(FlowInstanceNode::getInstanceCode, instanceCode));
        flowDetailVO.setNodeList(nodeList);

        EnumSet<FlowActionEnum> actions = flowService.countActions(instance, nodeList);
        flowDetailVO.setActions(actions);

        FlowModuleEnum flowModuleEnum = FlowModuleEnum.getById(instance.getModuleId());
        IFlowBaseService flowBaseService = SpringContextHolder.getBean(flowModuleEnum.getServiceClass());
        flowDetailVO.setApplyForm(flowBaseService.getApplyForm(instanceCode));

        return CommonResult.success(flowDetailVO);
    }
    
    /**
     * 批阅
     * @author: lijile
     * @date: 2021/11/2 15:45
     * @param taskCode
     * @param form
     * @return 
     */
    @ApiOperation("批阅")
    @PostMapping("/approve")
    public CommonResult approve(String taskCode, @Valid @RequestBody ApproveFormDTO form) {
        int action = form.getOpinion();
        if (action == NodeStatusEnum.AGREE.getStatus()) {
            flowService.agree(taskCode, form.getNote());
        } else if (action == NodeStatusEnum.DISAGREE.getStatus()) {
            flowService.disagree(taskCode, form.getNote());
        } else {
            return CommonResult.fail("操作不合法");
        }
        return CommonResult.success();
    }

    /**
     * 改派
     * @author: lijile
     * @date: 2021/11/3 15:15
     * @param taskCode
     * @param username
     * @return
     */
    @ApiOperation("改派")
    @PostMapping("/changeNode")
    public CommonResult changeNode(String taskCode, String username) {
        flowService.changeNode(taskCode, username);
        return CommonResult.success();
    }

    @Autowired private IFlowService flowService;

    @Autowired private IFlowInstanceService instanceService;

    @Autowired private IFlowInstanceNodeService instanceNodeService;

}
