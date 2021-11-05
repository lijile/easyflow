package org.lecoder.easyflow.modules.core.entity;

import java.time.LocalDateTime;
import org.lecoder.easyflow.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 流程实例表
 * </p>
 *
 * @author lijile
 * @since 2021-11-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FlowInstance extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 实例代码
     */
    private String instanceCode;

    /**
     * 定义代码
     */
    private String definitionCode;

    /**
     * 申请人用户名
     */
    private String username;

    /**
     * 申请人姓名
     */
    private String fullname;

    /**
     * 模块id
     */
    private String moduleId;

    /**
     * 模块名称
     */
    private String moduleName;

    /**
     * 申请标题
     */
    private String title;

    /**
     * 状态：0进行中，1已完成，2已终止等
     */
    private Integer status;

    /**
     * 申请时间
     */
    private LocalDateTime applyTime;


}
