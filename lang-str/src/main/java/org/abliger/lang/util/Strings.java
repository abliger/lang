package org.abliger.lang.util;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author abliger
 * @date 2020/12/3
 * @description
 */

public class Strings {
    /**
     * 转换为下划线
     *
     * @param camelCaseName
     * @return
     */
    public static String underscoreName(String camelCaseName) {
        StringBuilder result = new StringBuilder();
        if (camelCaseName != null && camelCaseName.length() > 0) {
            result.append(camelCaseName.substring(0, 1).toLowerCase());
            for (int i = 1; i < camelCaseName.length(); i++) {
                char ch = camelCaseName.charAt(i);
                if (Character.isUpperCase(ch)) {
                    result.append("_");
                    result.append(Character.toLowerCase(ch));
                } else {
                    result.append(ch);
                }
            }
        }
        return result.toString();
    }

    /**
     * 转换为下划线
     * @param camelCaseName
     * @param delimiter 分隔符
     * @return
     */
    public static String underscoreName(String camelCaseName,String delimiter) {
        final String[] split = camelCaseName.split(delimiter,Integer.MAX_VALUE);
        for (int i = 0; i < split.length; i++) {
            split[i] = underscoreName(split[i]);
        }
        return Arrays.stream(split).collect(Collectors.joining("."));
    }

    /**
     * 转换为驼峰
     *
     * @param underscoreName
     * @return
     */
    public static String camelCaseName(String underscoreName) {
        StringBuilder result = new StringBuilder();
        if (underscoreName != null && underscoreName.length() > 0) {
            boolean flag = false;
            for (int i = 0; i < underscoreName.length(); i++) {
                char ch = underscoreName.charAt(i);
                if ("_".charAt(0) == ch) {
                    flag = true;
                } else {
                    if (flag) {
                        result.append(Character.toUpperCase(ch));
                        flag = false;
                    } else {
                        result.append(ch);
                    }
                }
            }
        }
        return result.toString();
    }
}
