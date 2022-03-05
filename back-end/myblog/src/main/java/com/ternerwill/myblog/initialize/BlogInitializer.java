package com.ternerwill.myblog.initialize;

import com.ternerwill.myblog.beans.Blog;
import com.ternerwill.myblog.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
public class BlogInitializer {
    @Autowired
    BlogService blogService;

    public boolean insertBlogs(){
        BufferedReader reader=new BufferedReader(new InputStreamReader(BlogInitializer.class.getResourceAsStream("/blogs/springboot.md")));
        String content="";
        String title="飘摘选";
        String description="<<飘>>第一章第一段";
        do{
            try{
                String line=reader.readLine();
                if(line==null){
                    break;
                }
                content+=line+"\n";
            } catch (IOException e) {
                e.printStackTrace();
                log.error("读取md文件失败");
                return false;
            }

        }while(true);
        Blog blog=new Blog(title,description,content,"will terner");
        Boolean result=blogService.insertBlog(blog);
        return result;
    }
}
