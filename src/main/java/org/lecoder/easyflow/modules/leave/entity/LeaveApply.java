package org.lecoder.easyflow.modules.leave.entity;

import java.time.LocalDate;
import org.lecoder.easyflow.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 请假申请表
 * </p>
 *
 * @author lijile
 * @since 2021-10-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LeaveApply extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 实例代码
     */
    private String instanceCode;

    /**
     * 假期类型
     */
    private String type;

    /**
     * 请假天数
     */
    private Integer leaveDay;

    /**
     * 开始日期
     */
    private LocalDate startDate;

    /**
     * 结束日期
     */
    private LocalDate endDate;

    /**
     * 请假理由
     */
    private String reason;


}
