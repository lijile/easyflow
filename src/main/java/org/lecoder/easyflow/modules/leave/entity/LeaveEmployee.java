package org.lecoder.easyflow.modules.leave.entity;

import org.lecoder.easyflow.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 请假员工信息
 * </p>
 *
 * @author lijile
 * @since 2021-11-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LeaveEmployee extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 剩余年假天数
     */
    private Integer annualDays;


}
