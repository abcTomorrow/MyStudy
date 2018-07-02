package com.wojiushiwo.v1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CustomMapperProxy implements InvocationHandler {
    private CustomSqlSession sqlSession;

    public CustomMapperProxy(CustomSqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
    //以往的动态代理 存在接口的实现类 实际上以实现类调用method
    //而这里Mapper接口 未提供实现类 所有当执行Object自带方法的时候 可以使用代理类
    //而真正的查询时 则需要使用SqlSession来处理
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //如果方法来自于Mapper
        if(method.getDeclaringClass().getName().equals(CustomMapperParse.CustomerMapperName)){
            String sql = CustomMapperParse.map.get(method.getName());
            return sqlSession.selectById(sql,args[0]);
        }
        return method.invoke(this,args);
    }
}
