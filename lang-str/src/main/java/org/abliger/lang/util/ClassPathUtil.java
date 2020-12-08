package org.abliger.lang.util;

/**
 * @author abliger
 * @date 2020/12/5
 * @description
 */

public class ClassPathUtil {
    public static String getClassPath(){
        final String path = ClassPathUtil.class
                .getClassLoader()
                .getResource("")
                .getPath();
        return path.substring(1);
    }
}
