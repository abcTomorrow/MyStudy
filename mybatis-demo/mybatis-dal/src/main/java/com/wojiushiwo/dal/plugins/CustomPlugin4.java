package com.wojiushiwo.dal.plugins;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.plugin.*;

import java.sql.PreparedStatement;
import java.util.Properties;
@Intercepts({@Signature(type = ParameterHandler.class,method = "setParameters",args = {PreparedStatement.class})})
public class CustomPlugin4 implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("ParameterHandler");
        System.out.println("=================CustomPlugin4======================");
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
