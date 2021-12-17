package org.lecoder.easyflow.modules.core.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.lecoder.easyflow.common.web.CommonResult;
import org.lecoder.easyflow.modules.core.entity.FlowDefinition;
import org.lecoder.easyflow.modules.core.entity.FlowDefinitionNode;
import org.lecoder.easyflow.modules.core.service.IFlowDefinitionNodeService;
import org.lecoder.easyflow.modules.core.service.IFlowDefinitionService;
import org.lecoder.easyflow.modules.core.vo.DefinitionDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 管理员控制器
 *
 * @author lijile
 * @date 2021/12/16 17:18
 */
@Api(tags = "core.AdminController")
@RestController
@RequestMapping("/core/admin")
public class AdminController {

    /**
     * 流程定义详细
     * @author lijile
     * @date 2021/12/16 17:20
     * @return
     */
    @ApiOperation("流程定义详情")
    @GetMapping("/get-definition")
    public CommonResult getDefinition(String definitionCode) {
        LambdaQueryWrapper<FlowDefinition> definitionLambdaQueryWrapper = new LambdaQueryWrapper();
        definitionLambdaQueryWrapper.eq(FlowDefinition::getDefinitionCode, definitionCode);
        FlowDefinition flowDefinition = flowDefinitionService.getOne(definitionLambdaQueryWrapper);

        LambdaQueryWrapper<FlowDefinitionNode> definitionNodeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        definitionNodeLambdaQueryWrapper.eq(FlowDefinitionNode::getDefinitionCode, definitionCode);
        List<FlowDefinitionNode> flowDefinitionNodeList = flowDefinitionNodeService.list(definitionNodeLambdaQueryWrapper);

        DefinitionDetailVO definitionDetailVO = new DefinitionDetailVO();
        definitionDetailVO.setDefinition(flowDefinition);
        definitionDetailVO.setNodeList(flowDefinitionNodeList);
        return CommonResult.success(definitionDetailVO);
    }

    /**
     * 流程定义列表
     * @author lijile
     * @date 2021/12/17 17:56
     * @return
     */
    @ApiOperation("流程定义列表")
    @GetMapping("/list-definition")
    public CommonResult listDefinition() {
        return CommonResult.success(flowDefinitionService.list());
    }

    @Autowired private IFlowDefinitionService flowDefinitionService;

    @Autowired private IFlowDefinitionNodeService flowDefinitionNodeService;

}
