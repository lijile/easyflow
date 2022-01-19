package org.lecoder.easyflow.modules.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 节点类标识
 *
 * @author lijile
 * @date 2022/1/19 15:10
 */
@Target({ TYPE })
@Retention(RUNTIME)
@Documented
public @interface Node {
    String value() default "";
}
