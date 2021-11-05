package org.lecoder.easyflow.modules.leave.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * 申请列表
 *
 * @author: lijile
 * @date: 2021/10/29 14:42
 * @version: 1.0
 */
@Data
public class ApplyListDTO {

    /**
     * 实例代码
     */
    private String instanceCode;

    /**
     * 申请人用户名
     */
    private String username;

    /**
     * 申请人姓名
     */
    private String fullname;

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

    // ============== 申请单明细 =============

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
