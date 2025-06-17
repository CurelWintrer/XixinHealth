package com.neusoft.tijian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class TijianServerRunApplication {
    public static void main(String[] args) {
        SpringApplication.run(TijianServerRunApplication.class,args);
    }
}
