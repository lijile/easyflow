package org.lecoder.easyflow.modules.leave.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.lecoder.easyflow.common.constraint.Contains;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * 请假表单
 *
 * @author: lijile
 * @date: 2021/10/27 14:19
 * @version: 1.0
 */
@Data
@ApiModel("请假参数")
public class LeaveFormDTO {

    /**
     * 假期类型
     */
    @Contains(list = {"年假", "事假", "产假", "病假"}, message = "假期类型无效")
    private String type;

    /**
     * 开始日期
     */
    @NotNull(message = "开始日期不为空")
    private LocalDate startDate;

    /**
     * 结束日期
     */
    @NotNull(message = "结束日期不为空")
    private LocalDate endDate;

    /**
     * 请假理由
     */
    private String reason;

}
