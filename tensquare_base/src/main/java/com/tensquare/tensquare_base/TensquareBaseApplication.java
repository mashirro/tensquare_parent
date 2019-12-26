package com.tensquare.tensquare_base;

import com.tensquare.tensquare_common.util.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class TensquareBaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(TensquareBaseApplication.class, args);
	}

	@Bean
	public IdWorker idWorker(){
		return new IdWorker(1,1);
	}

}
