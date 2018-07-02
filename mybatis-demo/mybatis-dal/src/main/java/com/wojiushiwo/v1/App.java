package com.wojiushiwo.v1;

public class App {
    public static void main(String[] args) {
        CustomSqlSession sqlSession=new CustomSqlSession(new CustomConfiguration(),new CustomSimpleExecutor());
        CustomUserMapper customUserMapper = sqlSession.getMapper(CustomUserMapper.class);
        User user = customUserMapper.selectById("1");
        System.out.println(user);
    }
}
