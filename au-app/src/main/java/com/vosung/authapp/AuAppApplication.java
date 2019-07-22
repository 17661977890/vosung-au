package com.vosung.authapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import tk.mybatis.spring.annotation.MapperScan;
//@EnableElasticsearchRepositories(basePackages = "com.vosung.authapp.estest")
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableFeignClients
@ComponentScan(basePackages = {"com.vosung.authapp","com.vosung.boapi"})
@MapperScan("com.vosung.authapp.*.mapper")
public class AuAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuAppApplication.class, args);
    }

}
