package org.lecoder.easyflow.modules.leave.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.lecoder.easyflow.common.web.CommonResult;
import org.lecoder.easyflow.modules.leave.dto.ApplyListDTO;
import org.lecoder.easyflow.modules.leave.dto.LeaveFormDTO;
import org.lecoder.easyflow.modules.leave.dto.QueryFilterDTO;
import org.lecoder.easyflow.modules.leave.entity.LeaveApply;
import org.lecoder.easyflow.modules.leave.service.ILeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 请假控制器
 *
 * @author: lijile
 * @date: 2021/10/27 14:18
 * @version: 1.0
 */
@Api(tags = "leave.LeaveController")
@RestController
@RequestMapping("/leave")
public class LeaveController {

    /**
     * 提交请假表单
     * @author: lijile
     * @date: 2021/10/27 14:35
     * @param leaveForm
     * @return
     */
    @ApiOperation("提交请假申请")
    @PostMapping("/submit")
    public CommonResult submit(@Valid @RequestBody LeaveFormDTO leaveForm) {
        LeaveApply leaveApply = leaveService.submit(leaveForm);
        return CommonResult.success(leaveApply);
    }
    
    /**
     * 申请列表
     * @author: lijile
     * @date: 2021/10/29 14:27
     * @param queryFilter
     * @param pageNo
     * @param pageSize
     * @return 
     */
    @ApiOperation("申请列表")
    @GetMapping("/apply-list")
    public CommonResult applyList(QueryFilterDTO queryFilter,
                                  @RequestParam(defaultValue = "1") Integer pageNo,
                                  @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<ApplyListDTO> page = new Page<>(pageNo, pageSize);
        return CommonResult.success(leaveService.applyList(page, queryFilter));
    }

    @Autowired private ILeaveService leaveService;

}
