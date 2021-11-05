package org.lecoder.easyflow.modules.leave.dto;

import lombok.Data;

/**
 * 查询过滤器
 *
 * @author: lijile
 * @date: 2021/10/29 14:37
 * @version: 1.0
 */
@Data
public class QueryFilterDTO {
    /**
     * 假期类型
     */
    private String type;
    /**
     * 开始日期
     */
    private String startDate;

    /**
     * 结束日期
     */
    private String endDate;
}
