package org.lecoder.easyflow.modules.core.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.lecoder.easyflow.modules.core.entity.FlowInstance;
import org.lecoder.easyflow.modules.core.entity.FlowInstanceNode;
import org.lecoder.easyflow.modules.core.enums.FlowActionEnum;

import java.util.EnumSet;
import java.util.List;

/**
 * 流程详情
 *
 * @author: lijile
 * @date: 2021/10/27 15:20
 * @version: 1.0
 */
@ApiModel("流程详情")
@Data
public class FlowDetailVO {

    private FlowInstance instance;

    private List<FlowInstanceNode> nodeList;

    private EnumSet<FlowActionEnum> actions;

    private Object applyForm;

}
