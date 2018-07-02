package com.wojiushiwo.v1;

public interface CustomExecutor {
    <T> T selectOne(String sql,Object arg);
}
