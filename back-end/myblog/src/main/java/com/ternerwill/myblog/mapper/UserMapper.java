package com.ternerwill.myblog.mapper;

import com.ternerwill.myblog.beans.User;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;

@Mapper
public interface UserMapper {
    @Options(useGeneratedKeys=true,keyProperty="userId",keyColumn="user_id")
    @Insert("INSERT INTO user (username,password) VALUES(#{userName},#{password})")
    public void insertUser(User user);

    @Select("SELECT user_id userId, username userName,password,email,avatar FROM user WHERE username=#{userName}")
    public User getUserByUserName(String userName);

    @Update("UPDATE user set last_login=#{lastLogin}")
    public void updateUserLogin(LocalDateTime time);

    @Select("select user_id userId,username userName,password,email,avatar from user where user_id=#{userid}")
    public User getUserById(Integer userid);

}
