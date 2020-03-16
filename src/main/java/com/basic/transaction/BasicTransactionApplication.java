package com.basic.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BasicTransactionApplication {

	@GetMapping("/")
	@ResponseBody
	public String hello() {
		return "Hello from Basic TransactionApplication";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(BasicTransactionApplication.class, args);

	}

}
