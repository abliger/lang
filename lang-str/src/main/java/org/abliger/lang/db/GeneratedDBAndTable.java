package org.abliger.lang.db;

/**
 * @author abliger
 * @date 2020/12/3
 * @description
 */

public class GeneratedDBAndTable<T> {
    public GeneratedDBAndTable(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * 需要生成表的类
     */
    private Class<T> clazz;
}
