package com.orange.myrule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

@Configuration
public class MyseleRule {
	
	@Bean
	public IRule myRule() {
		
		return new RandomRule();//默认轮训，自定义为随机
	}

}
