package org.abliger.lang.util;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author abliger
 */

public class ArrayChange {
    /**
     * int数组转换为Integer数组
     * @param ints int数组
     * @return Integer数组
     */
    public static Integer[] intToInteger(int[] ints){
        IntStream stream = Arrays.stream(ints);
        Stream<Integer> boxed = stream.boxed();
        return boxed.toArray(Integer[]::new);
    }
    /**
     * Integer数组转换为int数组
     * @param ints Integer数组
     * @return int数组
     */
    public static int[] integerToInt(Integer[] ints){
        return Arrays.stream(ints).mapToInt(Integer::intValue).toArray();
    }

}
