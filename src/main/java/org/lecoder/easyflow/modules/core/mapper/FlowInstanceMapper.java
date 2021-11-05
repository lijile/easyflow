package org.lecoder.easyflow.modules.core.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.lecoder.easyflow.modules.core.dto.TaskInstanceDTO;
import org.lecoder.easyflow.modules.core.entity.FlowInstance;

/**
 * <p>
 * 流程实例表 Mapper 接口
 * </p>
 *
 * @author lijile
 * @since 2021-10-25
 */
public interface FlowInstanceMapper extends BaseMapper<FlowInstance> {
    IPage<TaskInstanceDTO> taskPage(IPage<TaskInstanceDTO> page, @Param(Constants.WRAPPER) Wrapper queryWrapper);
}
