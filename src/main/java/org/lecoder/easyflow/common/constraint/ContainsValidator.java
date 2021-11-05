package org.lecoder.easyflow.common.constraint;

import cn.hutool.core.util.StrUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * 检验器
 * @author: lijile
 * @date: 2021/10/27 14:33
 * @version: 1.0
 */
public class ContainsValidator implements ConstraintValidator<Contains, String> {

    boolean required;

    private String[] list;

    @Override
    public void initialize(Contains constraintAnnotation) {
        required = constraintAnnotation.required();
        list = constraintAnnotation.list();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (required) {
            return value != null && Arrays.stream(list).anyMatch(item -> item.equals(value));
        }
        return StrUtil.isEmpty(value) || Arrays.stream(list).anyMatch(item -> item.equals(value));
    }

}
