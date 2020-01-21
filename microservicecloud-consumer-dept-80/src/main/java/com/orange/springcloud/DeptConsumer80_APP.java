package com.orange.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

import com.orange.myrule.MyseleRule;

@EnableEurekaClient
@SpringBootApplication
@RibbonClient(name = "MICROSERVICECLOUD-DEPT" ,configuration = MyseleRule.class)
public class DeptConsumer80_APP {
	public static void main(String[] args) {
		SpringApplication.run(DeptConsumer80_APP.class, args);
	}
}
