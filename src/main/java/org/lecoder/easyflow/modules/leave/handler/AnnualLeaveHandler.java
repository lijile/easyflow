package org.lecoder.easyflow.modules.leave.handler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.lecoder.easyflow.common.exception.FlowException;
import org.lecoder.easyflow.modules.leave.dto.LeaveFormDTO;
import org.lecoder.easyflow.modules.leave.entity.LeaveApply;
import org.lecoder.easyflow.modules.leave.entity.LeaveEmployee;
import org.lecoder.easyflow.modules.leave.service.ILeaveEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;

/**
 * 年假处理器
 *
 * @author: lijile
 * @date: 2021/11/15 15:54
 * @version: 1.0
 */
@Component
public class AnnualLeaveHandler implements ILeaveTypeHandler {
    @Override
    public void beforeSubmit(String username, LeaveFormDTO leaveForm) {
        int days = (int) leaveForm.getStartDate().until(leaveForm.getEndDate(), ChronoUnit.DAYS) + 1;
        // 检查剩余天数
        LeaveEmployee leaveEmployee = leaveEmployeeService.getOne(new QueryWrapper<LeaveEmployee>().eq("username", username));
        if (leaveEmployee == null || leaveEmployee.getAnnualDays() < days) {
            throw new FlowException("剩余年假天数不足");
        }

        // 更新剩余年假天数
        leaveEmployee.setAnnualDays(leaveEmployee.getAnnualDays() - days);
        leaveEmployeeService.updateById(leaveEmployee);
    }

    @Override
    public void afterRollback(String username, LeaveApply leaveApply) {
        // 恢复年假天数
        int leaveDay = leaveApply.getLeaveDay();
        LeaveEmployee leaveEmployee = leaveEmployeeService.getOne(new QueryWrapper<LeaveEmployee>().eq("username", username));
        leaveEmployee.setAnnualDays(leaveEmployee.getAnnualDays() + leaveDay);
        leaveEmployeeService.updateById(leaveEmployee);
    }

    @Autowired private ILeaveEmployeeService leaveEmployeeService;
}
