package com.roadjava.psi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.roadjava.psi.mapper")
public class PsiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PsiApplication.class, args);
    }

}
