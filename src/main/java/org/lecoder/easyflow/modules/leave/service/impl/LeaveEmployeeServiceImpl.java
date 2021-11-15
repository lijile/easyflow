package org.lecoder.easyflow.modules.leave.service.impl;

import org.lecoder.easyflow.modules.leave.entity.LeaveEmployee;
import org.lecoder.easyflow.modules.leave.mapper.LeaveEmployeeMapper;
import org.lecoder.easyflow.modules.leave.service.ILeaveEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 请假员工信息 服务实现类
 * </p>
 *
 * @author lijile
 * @since 2021-11-15
 */
@Service
public class LeaveEmployeeServiceImpl extends ServiceImpl<LeaveEmployeeMapper, LeaveEmployee> implements ILeaveEmployeeService {

}
