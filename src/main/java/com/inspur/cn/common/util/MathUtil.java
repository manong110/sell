package com.inspur.cn.common.util;

public class MathUtil {

    private static final Double MATH_RANGE = 0.01;

    public static boolean equals(Double d1,Double d2){
        Double result = Math.abs(d1 - d2);
        if(result < MathUtil.MATH_RANGE){
            return true;
        }
        return false;
    }
}
