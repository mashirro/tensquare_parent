package com.tensquare.tensquare_user;

import com.tensquare.tensquare_common.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TensquareUserApplicationTests {

	@Autowired
	private JwtUtil jwtUtil;

	@Test
	void contextLoads() {
		String key = jwtUtil.getKey();
		System.out.println(key);
	}

}
