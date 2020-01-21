package com.orange.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.orange.springcloud.entities.Dept;

@RestController
public class DeptController_Consumer {
	
	//private static final String REST_URL_PREFIX = "http://localhost:8001";
	//改用微服务名访问(应为大写)
	private static final String REST_URL_PREFIX = "http://MICROSERVICECLOUD-DEPT";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value="/consumer/dept/add")
	public boolean add(@RequestBody Dept dept) {
		return restTemplate.postForObject(REST_URL_PREFIX+"/dept/add", dept, Boolean.class);
	}
	
	@RequestMapping(value="/consumer/dept/get")
	public Dept get(@PathVariable("id") Long id) {
		return restTemplate.getForObject(REST_URL_PREFIX+"/dept/get" , Dept.class);
	}

	@RequestMapping(value="/consumer/dept/list")
	public List<Dept> list() {
		System.out.println("ddd");
		return restTemplate.getForObject(REST_URL_PREFIX+"/dept/list" , List.class);
	}
	
	
}
