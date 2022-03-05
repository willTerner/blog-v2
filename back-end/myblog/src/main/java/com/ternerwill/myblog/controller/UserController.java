package com.ternerwill.myblog.controller;

import com.ternerwill.myblog.beans.Blog;
import com.ternerwill.myblog.beans.User;
import com.ternerwill.myblog.interceptor.Authentication;
import com.ternerwill.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RequestMapping("/api/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private Authentication authen;


    @PostMapping("/register")
    public boolean userRegister(@RequestParam("userName")String userName,@RequestParam("password")String password,HttpServletResponse response){
        userName = userName.toLowerCase();
        User isExisted=userService.getUserByUserName(userName);
        if(isExisted!=null){
            return false;
        }
        User user=new User(userName,password);
        if(userService.insertUser(user)){
            return true;
        }
        else{
            authen.serverError(response);
            return false;
        }
    }

    @PostMapping("/login")
    public Map<String,String> doLogin(@RequestParam("userName")String userName, @RequestParam("password")String password, HttpServletResponse response, HttpServletRequest request){
        User user=userService.getUserByUserName(userName);
        Map<String,String> ans  = new HashMap<>();
        if(user==null){
            ans.put("code","2");
            return ans;
        }
        if(!user.getPassword().equals(password)){
            ans.put("code","3");
            return ans;
        }
        Cookie cookie = new Cookie("userId",String.valueOf(user.getUserId()));
        cookie.setPath("/");
        response.addCookie(cookie);
        ans.put("userName",user.getUserName());
        ans.put("userId",String.valueOf((int)user.getUserId()));
        ans.put("code","0");
        return ans;
    }




}
