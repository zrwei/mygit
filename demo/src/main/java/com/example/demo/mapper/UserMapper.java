package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    /*在 MyBatis 里，#{} 占位符会被 MyBatis 解析为 PreparedStatement 的参数占位符。
    也就是说，MyBatis 会自动将 #{username} 和 #{password} 转换为 PreparedStatement 里的 ?
     占位符，并且使用 PreparedStatement 的 setXXX 方法来设置参数。*/
    @Select("SELECT * FROM users WHERE username = #{username} AND password = #{password}")
    User findUserByUsernameAndPassword(String username, String password);
}    