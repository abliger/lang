package org.abliger.lang.entity;

import lombok.Data;

/**
 * @author abliger
 * @date 2020/12/4
 * @description
 */
@Data
public class TableFiled {
    private String TableFiled;
    private boolean isPrimaryKey;
    private String description;
    /**
     * Table注解属性的类型
     */
    private Class<?> clazz;
}
