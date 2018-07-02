package com.wojiushiwo.v1;

import java.lang.reflect.Proxy;

public class CustomConfiguration {

    public <T> T getMapper(Class clazz,CustomSqlSession sqlSession){
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),new Class[]{clazz},new CustomMapperProxy(sqlSession));
    }


}
