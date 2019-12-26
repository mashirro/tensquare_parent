package com.tensquare.tensquare_spit;

import com.tensquare.tensquare_common.util.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TensquareSpitApplication {

	public static void main(String[] args) {
		SpringApplication.run(TensquareSpitApplication.class, args);
	}

	@Bean
	public IdWorker idWorker(){
		return new IdWorker(1,1);
	}

}
