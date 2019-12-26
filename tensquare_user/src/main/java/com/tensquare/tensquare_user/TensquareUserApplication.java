package com.tensquare.tensquare_user;

import com.tensquare.tensquare_common.util.IdWorker;
import com.tensquare.tensquare_common.util.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TensquareUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(TensquareUserApplication.class, args);
	}

	@Bean
	public IdWorker idWorker(){
		return new IdWorker(1,1);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public JwtUtil jwtUtil(){
		return new JwtUtil();
	}
}