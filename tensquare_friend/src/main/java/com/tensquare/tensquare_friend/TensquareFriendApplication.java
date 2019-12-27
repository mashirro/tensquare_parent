package com.tensquare.tensquare_friend;

import com.tensquare.tensquare_common.util.IdWorker;
import com.tensquare.tensquare_common.util.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TensquareFriendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TensquareFriendApplication.class, args);
	}

	@Bean
	public IdWorker idWorker(){
		return new IdWorker(1,1);
	}

	@Bean
	public JwtUtil jwtUtil(){
		return new JwtUtil();
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(RestTemplateBuilder builder){
		return builder.build();
	}

}
