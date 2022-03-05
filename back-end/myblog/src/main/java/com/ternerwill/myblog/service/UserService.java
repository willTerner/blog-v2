package com.ternerwill.myblog.service;

import com.ternerwill.myblog.beans.Blog;
import com.ternerwill.myblog.beans.User;
import com.ternerwill.myblog.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public boolean insertUser(User user){
        try{
            userMapper.insertUser(user);
        }
        catch(Exception ex){
            log.error("插入用户失败");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public User getUserByUserName(String userName){
        User user=null;
        try{
            user=userMapper.getUserByUserName(userName);
        }
        catch(Exception ex){
            log.error("根据用户名获取用户失败");
            ex.printStackTrace();
            return user;
        }
        return user;
    }

    public boolean updateUserLogin(LocalDateTime time){
        try{
            userMapper.updateUserLogin(time);
        }
        catch(Exception ex){
            log.error("更新用户登录时间失败");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean hasUser(int userid){
        try{
            User user = userMapper.getUserById(userid);
            if(user==null){
                return false;
            }else{
                return true;
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

}
