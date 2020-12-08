package org.abliger.lang.util;

/**
 * @author abliger
 */

public class ToString {
    /**
     * 循环打印数组内容
     * @param array 待打印数组
     * @param delimiter 分隔符
     * @param <T> 数组类型
     * @return 数组元素字符串
     */
    public static <T> String toStringArray(T[] array,char delimiter) {
        final StringBuilder str = new StringBuilder();
        for (T o : array) {
            str.append(o.toString()).append(delimiter);
        }
        return str.toString();
    }

    /**
     * 循环打印数组内容,按行打印
     * @param array 待打印数组
     * @param <T> 数组类型
     * @return 数组元素字符串
     */
    public static <T> String toStringArray(T[] array) {
        final StringBuilder str = new StringBuilder();
        for (T o : array) {
            str.append(o.toString()).append('\n');
        }
        return str.toString();
    }

    /**
     * 二维数组遍历输出
     * @param array 待输出二维数组
     * @param <T> 二维数组类型
     * @return 二维数组
     */
    public static <T> String toStringTwoDimensionalArray(T[][] array){
        StringBuilder str = new StringBuilder();
        for (T[] ts : array) {
            str.append(toStringArray(ts,'\t')).append('\n');
        }
        return str.toString();
    }
}
