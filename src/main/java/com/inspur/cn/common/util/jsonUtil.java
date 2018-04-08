package com.inspur.cn.common.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class jsonUtil {

    /**
     * 字符串转换为json
     * @param object
     * @return
     */
    public static String tojson(Object object){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = new GsonBuilder().create();
        return gson.toJson(gson);
    }

}
