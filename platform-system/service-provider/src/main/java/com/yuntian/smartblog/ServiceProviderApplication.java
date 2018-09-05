package com.yuntian.smartblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableScheduling
@ServletComponentScan
@SpringBootApplication
@EnableTransactionManagement //启用事务
@ImportResource("classpath:dubbo-provider.xml")
public class ServiceProviderApplication extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ServiceProviderApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ServiceProviderApplication.class, args);
    }
}
