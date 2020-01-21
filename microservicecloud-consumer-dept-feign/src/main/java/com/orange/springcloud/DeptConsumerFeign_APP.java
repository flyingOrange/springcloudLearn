package com.orange.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.orange.springcloud"})
@ComponentScan("com.orange.springcloud")
public class DeptConsumerFeign_APP {
	public static void main(String[] args) {
		SpringApplication.run(DeptConsumerFeign_APP.class, args);
	}
}
