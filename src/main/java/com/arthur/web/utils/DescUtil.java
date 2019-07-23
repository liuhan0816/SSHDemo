package com.arthur.web.utils;

import java.util.LinkedHashMap;

/**
 * @ClassName DescUtil
 * @Description 类型描述文件
 * @Author liuhan
 * @Date 2019/7/5 15:06
 * @Version 1.0
 **/
public class DescUtil {
    /**
     * @Author liuhan
     * @Description 性别类型描述
     * @Date 2019/7/5 15:14
     * @Param 
     * @Return 
     */
    public static LinkedHashMap<String, String> getGenderTypeDesc(){
        LinkedHashMap<String, String> types = new LinkedHashMap<String, String>();
        types.put("-1", "未知");
        types.put("0", "女");
        types.put("1", "男");
        return types;
    }
}
