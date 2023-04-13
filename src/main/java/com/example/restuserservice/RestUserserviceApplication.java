package com.example.restuserservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@MapperScan(basePackages = "com.example.restuserservice.mapper")
public class RestUserserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestUserserviceApplication.class, args);
    }

    @Bean
    //비밀번호 암호화를 위한 빈 설정
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
