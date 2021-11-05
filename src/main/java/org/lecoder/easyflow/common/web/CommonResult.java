package org.lecoder.easyflow.common.web;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 接口通用返回对象
 * @author: lijile
 * @date: 2021/10/25 13:50
 * @version: 1.0
 */
@Data
@ApiModel(value = "接口返回对象")
public class CommonResult<T> {

    private static final int SUCCESS = 200;

    private static final int ERROR = -1;

    @ApiModelProperty("返回代码")
    private int code;

    @ApiModelProperty("返回提示消息")
    private String msg;

    @ApiModelProperty("返回数据对象")
    private T data;

    public static CommonResult notlogin() {
        CommonResult commonResult = new CommonResult();
        commonResult.setCode(401);
        commonResult.setMsg("请先登录");
        return commonResult;
    }

    public static CommonResult success() {
        CommonResult commonResult = new CommonResult();
        commonResult.setCode(SUCCESS);
        commonResult.setMsg("success");
        return commonResult;
    }

    public static CommonResult success(String msg) {
        CommonResult commonResult = new CommonResult();
        commonResult.setCode(SUCCESS);
        commonResult.setMsg(msg);
        return commonResult;
    }

    public static <T> CommonResult success(T data) {
        CommonResult commonResult = new CommonResult();
        commonResult.setCode(SUCCESS);
        commonResult.setData(data);
        return commonResult;
    }

    public static <T> CommonResult success(String msg, T data) {
        CommonResult commonResult = success(msg);
        commonResult.setData(data);
        return commonResult;
    }

    public static CommonResult fail(String msg) {
        CommonResult commonResult = new CommonResult();
        commonResult.setCode(ERROR);
        commonResult.setMsg(msg);
        return commonResult;
    }

}
