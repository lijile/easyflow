package org.lecoder.easyflow.modules.core.dto;

import lombok.Data;

/**
 * 节点任务
 *
 * @author: lijile
 * @date: 2021/10/25 15:37
 * @version: 1.0
 */
@Data
public class NodeTaskDTO {
    /**
     * 实例代码
     */
    private String instanceCode;

    /**
     * 定义代码
     */
    private String definitionCode;

    /**
     * 节点代码
     */
    private String nodeCode;

    /**
     * 业务代码，一般指申请人用户名
     */
    private String businessCode;
}
