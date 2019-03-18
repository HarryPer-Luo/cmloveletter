package com.cmloveletter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.cmloveletter.dao")     //MyBatis注解扫描
@ServletComponentScan("com.cmloveletter.filter")    //拦截 过滤
@EnableCaching  //开启缓存
public class CmloveletterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmloveletterApplication.class, args);
    }



}

