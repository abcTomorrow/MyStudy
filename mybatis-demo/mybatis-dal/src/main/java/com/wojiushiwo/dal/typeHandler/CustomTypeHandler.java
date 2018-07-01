package com.wojiushiwo.dal.typeHandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@MappedJdbcTypes(value = JdbcType.VARCHAR)
public class CustomTypeHandler implements TypeHandler<String> {
    //设置字段属性值
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, String s, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i,s+"---");
    }
    //对返回数据 处理
    @Override
    public String getResult(ResultSet resultSet, String s) throws SQLException {
        String str = resultSet.getString(s);
        if(str.equals("wojiushiwo")){
            str="我就是我";
        }
        return str;
    }
    //对返回数据 处理
    @Override
    public String getResult(ResultSet resultSet, int i) throws SQLException {
        String str = resultSet.getString(i);
        if(str.equals("wojiushiwo")){
            str="我就是我";
        }
        return str;
    }

    @Override
    public String getResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
