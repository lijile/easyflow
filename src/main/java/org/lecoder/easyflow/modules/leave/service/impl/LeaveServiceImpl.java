package org.lecoder.easyflow.modules.leave.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.lecoder.easyflow.common.exception.FlowException;
import org.lecoder.easyflow.common.toolkit.Constants;
import org.lecoder.easyflow.common.toolkit.RequestHolder;
import org.lecoder.easyflow.modules.core.enums.FlowModuleEnum;
import org.lecoder.easyflow.modules.core.service.IFlowApiService;
import org.lecoder.easyflow.modules.leave.dto.ApplyListDTO;
import org.lecoder.easyflow.modules.leave.dto.LeaveFormDTO;
import org.lecoder.easyflow.modules.leave.dto.QueryFilterDTO;
import org.lecoder.easyflow.modules.leave.entity.LeaveApply;
import org.lecoder.easyflow.modules.leave.mapper.LeaveApplyMapper;
import org.lecoder.easyflow.modules.leave.service.ILeaveApplyService;
import org.lecoder.easyflow.modules.leave.service.ILeaveService;
import org.lecoder.easyflow.modules.sys.dto.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

/**
 * 请假服务实现
 *
 * @author: lijile
 * @date: 2021/11/1 13:59
 * @version: 1.0
 */
@Service
public class LeaveServiceImpl implements ILeaveService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public LeaveApply submit(LeaveFormDTO leaveForm) {
        int days = (int) leaveForm.getStartDate().until(leaveForm.getEndDate(), ChronoUnit.DAYS) + 1;
        if (days <= 0) {
            throw new FlowException("结束日期小于开始日期");
        }

        Map<String, Object> variables = new HashMap<>();
        variables.put("type", leaveForm.getType());
        variables.put("days", days);
        String instanceCode = flowApiService.start(FlowModuleEnum.LEAVE, "leave_common", variables);

        LeaveApply leaveApply = new LeaveApply();
        BeanUtils.copyProperties(leaveForm, leaveApply);
        leaveApply.setLeaveDay(days);
        leaveApply.setInstanceCode(instanceCode);
        leaveApplyService.save(leaveApply);
        return leaveApplyService.getOne(new QueryWrapper<LeaveApply>().eq(Constants.INSTANCE_CODE, instanceCode));
    }

    @Override
    public IPage<ApplyListDTO> applyList(Page<ApplyListDTO> page, QueryFilterDTO queryFilter) {
        UserDTO currentUser = RequestHolder.getCurrentUser();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", currentUser.getUsername());
        queryWrapper.eq("t1.module_id", FlowModuleEnum.LEAVE.getModuleId());
        queryWrapper.orderByDesc("start_date");
        if (queryFilter.getType() != null) {
            queryWrapper.eq("type", queryFilter.getType());
        }
        if (queryFilter.getStartDate() != null) {
            queryWrapper.ge("start_date", queryFilter.getStartDate());
        }
        if (queryFilter.getEndDate() != null) {
            queryWrapper.le("end_date", queryFilter.getEndDate());
        }
        return leaveApplyMapper.myPage(page, queryWrapper);
    }

    @Override
    public Object getApplyForm(String instanceCode) {
        return leaveApplyService.getOne(new QueryWrapper<LeaveApply>().eq(Constants.INSTANCE_CODE, instanceCode));
    }

    @Override
    public void flowTerminated(String instanceCode) {
        // 被动调用，恢复剩余假期天数等
    }

    @Autowired private IFlowApiService flowApiService;

    @Autowired private ILeaveApplyService leaveApplyService;

    @Autowired private LeaveApplyMapper leaveApplyMapper;
}
