package org.lecoder.easyflow.modules.core.entity;

import java.time.LocalDateTime;
import org.lecoder.easyflow.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 流程实例节点表
 * </p>
 *
 * @author lijile
 * @since 2021-11-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FlowInstanceNode extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 节点id
     */
    private Integer nodeId;

    /**
     * 父节点id
     */
    private Integer parentId;

    /**
     * 实例代码
     */
    private String instanceCode;

    /**
     * 任务编号
     */
    private String taskCode;

    /**
     * 审批人用户名
     */
    private String username;

    /**
     * 审批人姓名
     */
    private String fullname;

    /**
     * 节点代码
     */
    private String nodeCode;

    /**
     * 节点名称
     */
    private String nodeName;

    /**
     * 状态：0待处理，1同意，2不同意等
     */
    private Integer actionStatus;

    /**
     * 批阅时间
     */
    private LocalDateTime actionTime;

    /**
     * 批注
     */
    private String note;


}
