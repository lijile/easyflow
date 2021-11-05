package org.lecoder.easyflow.modules.core.entity;

import org.lecoder.easyflow.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 流程实例变量
 * </p>
 *
 * @author lijile
 * @since 2021-10-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FlowVariable extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 实例代码
     */
    private String instanceCode;

    /**
     * 变量键
     */
    private String keyy;

    /**
     * 变量值
     */
    private String valuee;

    /**
     * 变量类型
     */
    private String type;


}
