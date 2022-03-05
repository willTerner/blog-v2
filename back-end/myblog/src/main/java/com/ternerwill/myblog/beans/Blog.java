package com.ternerwill.myblog.beans;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ternerwill.myblog.deserializer.CustomDateTimeDeserializer;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Blog {
    private String title;
    private String description;
    private String content;
    private Date createTime;
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    private Date lastEdit;
    private String userName;
    private Integer blogId;

    public Blog(String title,String description,String content,String userName){
        this.title=title;
        this.description=description;
        this.content=content;
        this.userName=userName;
        this.createTime=new Date();
        this.lastEdit= new Date();
    }

    public Blog(){}


}
