package org.lecoder.easyflow.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.lecoder.easyflow.common.web.CommonResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常统一处理
 *
 * @author: lijile
 * @date: 2021/10/25 13:46
 * @version: 1.0
 */
@Slf4j
@ControllerAdvice
public class MyExceptionHandler {

    /**
     * 参数验证失败异常处理
     * 优势：无需在每个controller的方法处添加 BindingResult
     * @author: lijile
     * @date: 2021/10/27 9:40
     * @param e
     * @return 
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return CommonResult.fail(e.getBindingResult().getFieldError().getDefaultMessage());
    }
    
    /**
     * 流程异常处理
     * @author: lijile
     * @date: 2021/10/25 14:07
     * @return
     */
    @ResponseBody
    @ExceptionHandler(FlowException.class)
    public CommonResult flowException(FlowException e) {
        return CommonResult.fail(e.getMessage());
    }

    /**
     * 未设置异常处理，统一执行返回系统异常错误
     * @author: lijile
     * @date: 2021/10/25 14:06
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public CommonResult exception(Exception e) {
        log.error("【未知错误】", e);
        return CommonResult.fail("系统异常，请稍后重试！");
    }

}
