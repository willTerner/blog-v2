package com.ternerwill.myblog.service;

import com.ternerwill.myblog.beans.Blog;
import com.ternerwill.myblog.mapper.BlogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BlogService {

    @Autowired
    BlogMapper blogMapper;

    public boolean insertBlog(Blog blog){
        try{
            blogMapper.insertBlog(blog);
        }
        catch(Exception ex){
            ex.printStackTrace();
            log.error("插入博客失败");
            return false;
        }
        return true;
    }

    public List<Blog> getBlogs(int limit,int offset){
        try{
            List<Blog> blogs=blogMapper.getBlogs(limit,offset);
            return blogs;
        }
        catch(Exception ex){
            log.error("查询博客失败");
            ex.printStackTrace();
        }
        return null;
    }

    public int getBlogNumber(){
        int num=-1;
        try{
            num=blogMapper.getBlogNumber();
        }
        catch(Exception ex){
            log.error("查询博客数量失败");
            ex.printStackTrace();
            return num;
        }
        return num;
    }

    public Blog getBlogById(int blogId){
        Blog blog=null;
        try{
            blog=blogMapper.getBlogById(blogId);
        }catch(Exception ex){
            log.error("获取id为{}博客内容失败",blogId);
            ex.printStackTrace();
            return blog;
        }
        return blog;

    }
    public boolean updateBlog(Blog blog){
        try{
            blogMapper.updateBlog(blog);
        }
        catch(Exception ex){
            log.error("更新博客失败");
            ex.printStackTrace();
            return false;
        }
        return true;
    }


    public List<Blog> getUserBlogs(String userName){
        try{
            List<Blog> blogs = blogMapper.getUserBlogs(userName);
            return blogs;
        }catch(Exception ex){
            ex.printStackTrace();
            throw ex;
        }
    }
}
