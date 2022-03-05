package com.ternerwill.myblog.interceptor;

import com.ternerwill.myblog.beans.User;
import com.ternerwill.myblog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@Component
@Slf4j
public class Authentication {



    @Autowired
    UserService userService;


    public boolean validate(HttpServletRequest request, HttpServletResponse response)  {
        Cookie[] cookies = request.getCookies();
        if(cookies==null){
            clientError(response);
            return false;
        }
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("userId")){
                Integer userid = Integer.valueOf(cookie.getValue());
                boolean ans = userService.hasUser(userid);
                if(ans){
                    return true;
                }else{
                    unauthen(response,"没有认证");
                    return false;
                }
            }
        }
        unauthen(response,"unauthorized");
        return false;
    }


    public void clientError(HttpServletResponse response){
        response.setStatus(400);
    }

    public void unauthen(HttpServletResponse response,String message)  {
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            response.setStatus(401);
            writer.print(message);
            return ;
        } catch (IOException e) {
            e.printStackTrace();
            serverError(response);
        }
    }


    public void serverError(HttpServletResponse response){
        response.setStatus(500);
    }


}
