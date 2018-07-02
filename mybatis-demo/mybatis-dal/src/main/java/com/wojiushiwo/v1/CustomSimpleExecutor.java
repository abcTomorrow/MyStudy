package com.wojiushiwo.v1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomSimpleExecutor implements CustomExecutor{
    @Override
        public <T> T selectOne(String sql, Object arg){
           try{
               Class.forName("com.mysql.jdbc.Driver");
               Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/study", "root", "root");
               PreparedStatement ps = connection.prepareStatement(sql);
               ps.setString(1,arg.toString());
               ResultSet resultSet = ps.executeQuery();
               User user=null;
               while(resultSet.next()){
                   user=new User();
                   user.setId(resultSet.getString(1));
                   user.setUserName(resultSet.getString(2));
                   user.setPassword(resultSet.getString(3));
               }
               return (T) user;
           }catch (Exception e){
               e.printStackTrace();
           }
            return null;
        }
}
