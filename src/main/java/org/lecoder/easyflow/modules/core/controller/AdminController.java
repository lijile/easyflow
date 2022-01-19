package org.lecoder.easyflow.modules.core.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.lecoder.easyflow.EasyflowApplication;
import org.lecoder.easyflow.common.web.CommonResult;
import org.lecoder.easyflow.modules.core.annotation.Node;
import org.lecoder.easyflow.modules.core.dto.DefinitionFormDTO;
import org.lecoder.easyflow.modules.core.dto.DefinitionNodeFormDTO;
import org.lecoder.easyflow.modules.core.entity.FlowDefinition;
import org.lecoder.easyflow.modules.core.entity.FlowDefinitionNode;
import org.lecoder.easyflow.modules.core.service.IFlowDefinitionNodeService;
import org.lecoder.easyflow.modules.core.service.IFlowDefinitionService;
import org.lecoder.easyflow.modules.core.vo.DefinitionDetailVO;
import org.lecoder.easyflow.modules.core.vo.NodeRelClassVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 管理员控制器
 *
 * @author lijile
 * @date 2021/12/16 17:18
 */
@Api(tags = "core.AdminController")
@RestController
@RequestMapping("/core/admin")
public class AdminController implements ApplicationListener<ContextRefreshedEvent> {

    /**
     * 流程定义详细
     * @author lijile
     * @date 2021/12/16 17:20
     * @return
     */
    @ApiOperation("流程定义详情")
    @GetMapping("/get-definition")
    public CommonResult getDefinition(String definitionCode) {
        return CommonResult.success(getDefinitionDetail(definitionCode));
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

    /**
     * 创建流程定义
     * @author lijile
     * @date 2022/1/17 14:14
     * @return
     */
    @ApiOperation("创建流程定义")
    @PostMapping("/save-definition")
    public CommonResult saveDefinition(@Valid @RequestBody DefinitionFormDTO definitionForm) {
        flowDefinitionService.saveDefinition(definitionForm);

        FlowDefinition flowDefinition = flowDefinitionService.getByCode(definitionForm.getDefinitionCode());
        DefinitionDetailVO definitionDetailVO = new DefinitionDetailVO();
        definitionDetailVO.setDefinition(flowDefinition);
        return CommonResult.success(definitionDetailVO);
    }

    /**
     * 更新流程定义
     * @author lijile
     * @date 2022/1/18 9:46
     * @param definitionForm
     * @return
     */
    @ApiOperation("更新流程定义")
    @PutMapping("/update-definition")
    public CommonResult updateDefinition(@Valid @RequestBody DefinitionFormDTO definitionForm) {
        FlowDefinition flowDefinition = flowDefinitionService.getByCode(definitionForm.getDefinitionCode());
        flowDefinition.setDefinitionName(definitionForm.getDefinitionName());
        flowDefinitionService.updateById(flowDefinition);
        return CommonResult.success(flowDefinition);
    }

    /**
     * 删除流程定义
     * @author lijile
     * @date 2022/1/18 14:33
     * @param definitionCode
     * @return
     */
    @ApiOperation("删除流程定义")
    @DeleteMapping("/delete-definition")
    public CommonResult deleteDefinition(String definitionCode) {
        flowDefinitionService.deleteDefinition(definitionCode);
        return CommonResult.success();
    }

    /**
     * 创建流程节点
     * @author lijile
     * @date 2022/1/17 15:00
     * @param definitionNodeForm
     * @return
     */
    @ApiOperation("创建流程节点")
    @PostMapping("/save-definition-node")
    public CommonResult saveDefinitionNode(@Valid @RequestBody DefinitionNodeFormDTO definitionNodeForm) {
        flowDefinitionNodeService.saveDefinitionNode(definitionNodeForm);
        return CommonResult.success(flowDefinitionNodeService.getByCode(definitionNodeForm.getDefinitionCode(), definitionNodeForm.getNodeCode()));
    }

    /**
     * 更新流程节点
     * @author lijile
     * @date 2022/1/18 10:37
     * @param definitionNodeForm
     * @return
     */
    @ApiOperation("更新流程节点")
    @PutMapping("/update-definition-node")
    public CommonResult updateDefinitionNode(@Valid @RequestBody DefinitionNodeFormDTO definitionNodeForm) {
        flowDefinitionNodeService.updateDefinitionNode(definitionNodeForm);
        return CommonResult.success(flowDefinitionNodeService.getByCode(definitionNodeForm.getDefinitionCode(), definitionNodeForm.getNodeCode()));
    }

    /**
     * 删除流程节点
     * @author lijile
     * @date 2022/1/18 14:30
     * @param definitionCode
     * @param nodeCode
     * @return
     */
    @ApiOperation("删除流程节点")
    @DeleteMapping("/delete-definition-node")
    public CommonResult deleteDefinitionNode(String definitionCode, String nodeCode) {
        FlowDefinitionNode flowDefinitionNode = flowDefinitionNodeService.getByCode(definitionCode, nodeCode);
        LambdaQueryWrapper<FlowDefinitionNode> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FlowDefinitionNode::getDefinitionCode, flowDefinitionNode.getDefinitionCode());
        queryWrapper.eq(FlowDefinitionNode::getParentCode, flowDefinitionNode.getNodeCode());
        List<FlowDefinitionNode> children = flowDefinitionNodeService.list(queryWrapper);
        children.forEach(definitionNode -> definitionNode.setParentCode(flowDefinitionNode.getParentCode()));

        flowDefinitionNodeService.deleteDefinitionNode(definitionCode, nodeCode);
        return CommonResult.success(children);
    }

    /**
     * 搜索关联类
     * @author lijile
     * @date 2022/1/19 15:21
     * @param keyword
     * @return
     */
    @ApiOperation("搜索关联类")
    @GetMapping("/search-rel-class")
    public CommonResult searchRelClass(String keyword) {
        List<NodeRelClassVO> result = relClassList.stream().filter(
                relClass -> StringUtils.isBlank(keyword) || relClass.getName().indexOf(keyword) > -1 || relClass.getNote().indexOf(keyword) > -1)
                .limit(10)
                .collect(Collectors.toList());
        return CommonResult.success(result);
    }

    public DefinitionDetailVO getDefinitionDetail(String definitionCode) {
        LambdaQueryWrapper<FlowDefinition> definitionLambdaQueryWrapper = new LambdaQueryWrapper();
        definitionLambdaQueryWrapper.eq(FlowDefinition::getDefinitionCode, definitionCode);
        FlowDefinition flowDefinition = flowDefinitionService.getOne(definitionLambdaQueryWrapper);

        LambdaQueryWrapper<FlowDefinitionNode> definitionNodeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        definitionNodeLambdaQueryWrapper.eq(FlowDefinitionNode::getDefinitionCode, definitionCode);
        List<FlowDefinitionNode> flowDefinitionNodeList = flowDefinitionNodeService.list(definitionNodeLambdaQueryWrapper);

        DefinitionDetailVO definitionDetailVO = new DefinitionDetailVO();
        definitionDetailVO.setDefinition(flowDefinition);
        definitionDetailVO.setNodeList(flowDefinitionNodeList);
        return definitionDetailVO;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // 扫描包路径
        String pkgPath = EasyflowApplication.class.getPackage().getName();
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        TypeFilter includeFilter = (metadataReader, metadataReaderFactory) -> true;
        provider.addIncludeFilter(includeFilter);
        // 指定扫描的包名
        Set<BeanDefinition> candidateComponents = provider.findCandidateComponents(pkgPath);

        candidateComponents.forEach(beanDefinition -> {
            try {
                Class clazz = Class.forName(beanDefinition.getBeanClassName());
                Node node = (Node) clazz.getAnnotation(Node.class);
                if (node != null) {
                    NodeRelClassVO relClass = new NodeRelClassVO();
                    relClass.setName(clazz.getName());
                    relClass.setSimpleName(clazz.getSimpleName());
                    relClass.setNote(node.value());
                    relClassList.add(relClass);
                }
            } catch (ClassNotFoundException e) {

            }
        });
    }

    private List<NodeRelClassVO> relClassList = new LinkedList<>();

    @Autowired private IFlowDefinitionService flowDefinitionService;

    @Autowired private IFlowDefinitionNodeService flowDefinitionNodeService;

}
