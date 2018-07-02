package com.wojiushiwo.v1;

public class CustomSqlSession {
private CustomConfiguration customConfiguration;
private CustomExecutor customExecutor;

    public CustomSqlSession(CustomConfiguration customConfiguration, CustomExecutor customExecutor) {
        this.customConfiguration = customConfiguration;
        this.customExecutor = customExecutor;
    }

    public <T> T getMapper(Class clazz) {
        return customConfiguration.getMapper(clazz,this);
    }

    public <T> T selectById(String sql, Object arg) {
        return customExecutor.selectOne(sql,arg);
    }
}
