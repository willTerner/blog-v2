package com.ternerwill.myblog;

import com.ternerwill.myblog.initialize.BlogInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MyblogApplication {

	public static void main(String[] args) {
		ApplicationContext container=SpringApplication.run(MyblogApplication.class, args);

	}

}
