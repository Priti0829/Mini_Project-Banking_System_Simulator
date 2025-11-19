package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankingSystemProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingSystemProjectApplication.class, args);
        System.out.println("SPRING BOOT SERVER STARTED ON PORT 8080");
	}

}
