package org.lecoder.easyflow.modules.core.dto;

import lombok.Data;

/**
 * 任务过滤
 *
 * @author: lijile
 * @date: 2021/11/4 11:25
 * @version: 1.0
 */
@Data
public class TaskFilterDTO {

    /**
     * 模块id
     */
    private String moduleId;
    /**
     * 开始日期
     */
    private String startDate;

    /**
     * 结束日期
     */
    private String endDate;

}
