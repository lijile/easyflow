package org.lecoder.easyflow.modules.core.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 任务实例
 *
 * @author: lijile
 * @date: 2021/11/4 16:35
 * @version: 1.0
 */
@Data
public class TaskInstanceDTO {

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



    /**
     * 任务编号
     */
    private String taskCode;

    /**
     * 状态：0待处理，1同意，2不同意等
     */
    private Integer actionStatus;

}
