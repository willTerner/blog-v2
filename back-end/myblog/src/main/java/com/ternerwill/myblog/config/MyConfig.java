package com.ternerwill.myblog.config;

import com.ternerwill.myblog.interceptor.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfig {

    private String url="http://localhost:3000";

    @Autowired
    private Authentication interceptor;




    @Profile(value={"dev"})
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer webMvcConfigurer=new WebMvcConfigurer(){
            @Override
            public void addCorsMappings(CorsRegistry registry){

                registry.addMapping("/api/**")
                        .allowedOrigins(url)
                        .allowedMethods("POST","GET","PUT","DELETE","OPTIONS")
                        .allowedHeaders("content-type")
                        .allowCredentials(true);
            }


        };
        return webMvcConfigurer;
    }
}
