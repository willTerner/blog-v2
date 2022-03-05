package com.ternerwill.myblog.mapper;

import com.ternerwill.myblog.beans.Blog;
import com.ternerwill.myblog.beans.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BlogMapper {
    @Options(useGeneratedKeys=true,keyProperty="blogId",keyColumn="blog_id")
    @Insert("INSERT INTO blog (title,description,content,user_name,last_edit) VALUES(#{title},#{description},#{content},#{userName},#{lastEdit})")
    public void insertBlog(Blog blog);

    @Select("SELECT blog_id blogId,title,description,user_name userName,last_edit lastEdit from blog LIMIT #{limit} OFFSET #{offset}")
    public List<Blog> getBlogs(int limit,int offset);

    @Select("SELECT count(*) FROM blog")
    public int getBlogNumber();

    @Select("SELECT blog_id blogId,title,content,description,user_name userName,last_edit lastEdit FROM blog WHERE blog_id=#{blogId}")
    public Blog getBlogById(int blogId);

    @Update("UPDATE blog set content=#{content},title=#{title},description=#{description},last_edit=#{lastEdit} WHERE blog_id=#{blogId}")
    public void updateBlog(Blog blog);

    @Select("select blog_id blogId,title,description,user_name userName,last_edit lastEdit from blog where user_name=#{userName}")
    public List<Blog> getUserBlogs(String userName);

}
