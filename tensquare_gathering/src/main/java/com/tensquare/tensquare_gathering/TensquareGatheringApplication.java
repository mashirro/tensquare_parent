package com.tensquare.tensquare_gathering;

import com.tensquare.tensquare_common.util.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class TensquareGatheringApplication {

	public static void main(String[] args) {
		SpringApplication.run(TensquareGatheringApplication.class, args);
	}

	@Bean
	public IdWorker idWorker(){
		return new IdWorker(1,1);
	}
}
