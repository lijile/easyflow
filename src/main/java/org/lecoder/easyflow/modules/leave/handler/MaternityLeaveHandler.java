package org.lecoder.easyflow.modules.leave.handler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.lecoder.easyflow.common.exception.FlowException;
import org.lecoder.easyflow.modules.leave.dto.LeaveFormDTO;
import org.lecoder.easyflow.modules.leave.entity.LeaveEmployee;
import org.lecoder.easyflow.modules.leave.service.ILeaveEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 产假处理器
 *
 * @author: lijile
 * @date: 2021/11/15 16:38
 * @version: 1.0
 */
@Component
public class MaternityLeaveHandler implements ILeaveTypeHandler {
    @Override
    public void beforeSubmit(String username, LeaveFormDTO leaveForm) {
        LeaveEmployee leaveEmployee = leaveEmployeeService.getOne(new QueryWrapper<LeaveEmployee>().eq("username", username));
        if (leaveEmployee == null || leaveEmployee.getGender() != 1) {
            throw new FlowException("目前只支持女同志申请产假");
        }
    }
    @Autowired private ILeaveEmployeeService leaveEmployeeService;
}
