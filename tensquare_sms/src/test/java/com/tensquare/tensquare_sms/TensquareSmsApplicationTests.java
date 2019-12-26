package com.tensquare.tensquare_sms;

import com.tensquare.tensquare_sms.properties.SmsProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TensquareSmsApplicationTests {

	@Autowired
	private SmsProperties smsProperties;

	@Test
	void contextLoads() {
		System.out.println(smsProperties);
	}

}
