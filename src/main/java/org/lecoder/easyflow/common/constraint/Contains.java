package org.lecoder.easyflow.common.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 检验值是否存在数组中
 * @author: lijile
 * @date: 2021/10/27 14:24
 * @version: 1.0
 */
@Documented
@Constraint(validatedBy = ContainsValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Contains {

    boolean required() default true;

    String[] list() default {};

    String message() default "无效值";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
