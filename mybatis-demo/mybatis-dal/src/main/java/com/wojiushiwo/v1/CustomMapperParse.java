package com.wojiushiwo.v1;

import com.google.common.collect.Maps;

import java.util.Map;

public class CustomMapperParse {
    public static final String CustomerMapperName="com.wojiushiwo.v1.CustomUserMapper";
    public static Map<String,String> map=Maps.newHashMap();
    //模拟mybatis启动时 便把所有的mapper中mthod与sql的对应关系加载到
   static {
       map.put("selectById","select * from user where id=?");
   }
}
