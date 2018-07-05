package com.wojiushiwo.dal.plugins;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

@Intercepts({@Signature(type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class CustomPlugins implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        BoundSql boundSql = mappedStatement.getBoundSql(invocation.getArgs()[1]);
        System.out.println(String.format("plugin output sql = %s , param=%s", boundSql.getSql(), boundSql.getParameterObject()));
        System.out.println("======================customPlugins======================");
        return invocation.proceed();

    }

    @Override
    public Object plugin(Object o) {
        Object obj = Plugin.wrap(o, this);
        return obj;
    }

    @Override
    public void setProperties(Properties properties) {
        //常用于将配置中的参数赋值给类的实例变量
        String value = (String) properties.get("name");
        System.out.println(value);
    }
}
