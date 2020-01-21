package com.orange.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.orange.springcloud.entities.Dept;
import com.orange.springcloud.service.DeptService;

@RestController
public class DeptController {
	
	@Autowired
	private DeptService deptService;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@RequestMapping(value="/dept/discovery" , method=RequestMethod.GET)
	public Object discovery() {
		List<String> list = discoveryClient.getServices();
		System.out.println("list = "+list);
		
		List<ServiceInstance> servList = discoveryClient.getInstances("MICROSERVICECLOUD-DEPT");
		servList.forEach(server->{
			System.out.println(server.getServiceId() + "\t" + server.getHost() + "\t" + server.getPort() + "\t" +server.getUri());
			
		});
		return this.discoveryClient;
	}
	
	@RequestMapping(value="/dept/add" , method=RequestMethod.POST)
	public boolean add(@RequestBody Dept dept) {
		return deptService.add(dept);
	}
	
	@RequestMapping(value="/dept/list" , method=RequestMethod.GET)
	public List<Dept> list(){
		return deptService.list();
	}
	
	@RequestMapping(value="/dept/get/{id}" , method=RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "processHystrix_Get")
	public Dept get(@PathVariable("id") Long id ) {
		 Dept dept = deptService.get(id);
		 if(dept == null) {
			 throw new RuntimeException("该id没有对应的信息");
		 }
		 return dept;
	}
	
	public Dept processHystrix_Get(@PathVariable("id") Long id) {
		return new Dept().setDeptno(id).setDb_source("no this database").setDname("该id不存在"+id);
		
	}
	
	
	

}
