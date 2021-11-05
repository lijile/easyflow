package org.lecoder.easyflow.modules.leave.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.lecoder.easyflow.modules.leave.dto.ApplyListDTO;
import org.lecoder.easyflow.modules.leave.entity.LeaveApply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 请假申请表 Mapper 接口
 * </p>
 *
 * @author lijile
 * @since 2021-10-27
 */
public interface LeaveApplyMapper extends BaseMapper<LeaveApply> {
    IPage<ApplyListDTO> myPage(IPage<ApplyListDTO> page, @Param(Constants.WRAPPER) Wrapper queryWrapper);
}
