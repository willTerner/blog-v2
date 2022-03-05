package com.ternerwill.myblog.beans;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private String userName;
    private String password;
    private String avatar;
    private Integer userId;
    private String email;
    private LocalDateTime createTime;
    private LocalDateTime lastLogin;

    /***
     * 用于注册用户
     * @param userName
     * @param password
     */
    public User(String userName,String password){
        this.userName=userName;
        this.password=password;
        this.createTime=LocalDateTime.now();
    }

    public User(){}

}
