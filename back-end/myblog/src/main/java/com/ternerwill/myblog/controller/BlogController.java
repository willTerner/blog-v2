package com.ternerwill.myblog.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ternerwill.myblog.beans.Blog;
import com.ternerwill.myblog.interceptor.Authentication;
import com.ternerwill.myblog.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private Authentication authen;

    @Autowired
    private ObjectMapper mapper;

    @PostMapping("/blogs")
    public List<Blog> getBlogs(@RequestParam("limit")Integer limit, @RequestParam("offset")Integer offset){
        List<Blog> blogs=blogService.getBlogs(limit,offset);
        return blogs;
    }

    @GetMapping("/number")
    public Integer getBlogNumber(){
        int number=blogService.getBlogNumber();
        return number;
    }


    @PostMapping("/get")
    public Blog getBlog(@RequestParam("blogId")Integer blogId){
        Blog blog=blogService.getBlogById(blogId);
        return blog;
    }


    @PostMapping("/update")
    public Blog updateBlog(HttpServletRequest request, HttpServletResponse response){
        boolean ans = authen.validate(request,response);
        if(!ans){
            return null;
        }
        Blog blog;
        try{
            log.info("test");
            blog = resolveBlog(request);
        }catch(Exception ex){
            ex.printStackTrace();
            authen.serverError(response);
            return null;
        }
        if(!blogService.updateBlog(blog)){
            authen.serverError(response);
        }
        return blog;
    }

    @PostMapping("/add")
    public Blog addBlog(HttpServletResponse response,HttpServletRequest request){
        boolean ans = authen.validate(request,response);
        if(!ans){
            return null;
        }
        Blog blog = null;
        try {
            blog = resolveBlog(request);
        } catch (IOException e) {
            authen.serverError(response);
            e.printStackTrace();
            return null;
        }
        if(!blogService.insertBlog(blog)){
            authen.serverError(response);
        }
        return blog;
    }

    private Blog resolveBlog(HttpServletRequest request) throws IOException {
        Blog ans = new Blog();
        BufferedReader reader = request.getReader();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        return mapper.readValue(reader.lines().collect(Collectors.joining()), Blog.class);
    }

    @PostMapping("/user/blogs")
    public List<Blog> getBlogs(@RequestParam("userName")String userName,HttpServletRequest request ,HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        if(cookies==null){
            authen.unauthen(response,"unauthorized");
            return null;
        }
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("userId")){
                try{
                    List<Blog> blogs = blogService.getUserBlogs(userName);
                    return blogs;
                }catch(Exception ex){
                    authen.serverError(response);
                    return null;
                }
            }
        }
        authen.unauthen(response,"unauthorized");
        return null;
    }
}
