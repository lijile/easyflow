package org.lecoder.easyflow.modules.leave.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.lecoder.easyflow.modules.core.service.IFlowBaseService;
import org.lecoder.easyflow.modules.leave.entity.LeaveApply;
import org.lecoder.easyflow.modules.leave.mapper.LeaveApplyMapper;
import org.lecoder.easyflow.modules.leave.service.ILeaveApplyService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 请假申请表 服务实现类
 * </p>
 *
 * @author lijile
 * @since 2021-10-27
 */
@Service
public class LeaveApplyServiceImpl extends ServiceImpl<LeaveApplyMapper, LeaveApply> implements ILeaveApplyService, IFlowBaseService {

}
