package com.inspur.cn.common.util;

import lombok.Synchronized;

import java.util.Random;

public class KeyUtil {

    @Synchronized
    public static String getKeyValue(){
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis()+String.valueOf(number);
    }
}
