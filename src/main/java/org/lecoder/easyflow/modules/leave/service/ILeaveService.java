package org.lecoder.easyflow.modules.leave.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.lecoder.easyflow.modules.core.service.IFlowBaseService;
import org.lecoder.easyflow.modules.leave.dto.ApplyListDTO;
import org.lecoder.easyflow.modules.leave.dto.LeaveFormDTO;
import org.lecoder.easyflow.modules.leave.dto.QueryFilterDTO;
import org.lecoder.easyflow.modules.leave.entity.LeaveApply;

/**
 * 请假接口
 *
 * @author: lijile
 * @date: 2021/11/1 13:58
 * @version: 1.0
 */
public interface ILeaveService extends IFlowBaseService {
    /**
     * 提交表单
     * @author: lijile
     * @date: 2021/10/27 14:41
     * @param leaveForm
     * @return
     */
    LeaveApply submit(LeaveFormDTO leaveForm);

    /**
     * 申请列表
     * @author: lijile
     * @date: 2021/10/29 15:02
     * @param page
     * @param queryFilter
     * @return
     */
    IPage<ApplyListDTO> applyList(Page<ApplyListDTO> page, QueryFilterDTO queryFilter);
}
