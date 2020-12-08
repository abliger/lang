package org.abliger.lang.db;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author abliger
 * @date 2020/12/3
 * @description
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Table {
    //表名字段
    String value() default "";
    //是否是主键
    boolean isPrimaryKey() default false;
    //描述
    String description() default "";
}
