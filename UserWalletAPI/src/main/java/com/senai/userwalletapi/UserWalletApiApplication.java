package com.senai.userwalletapi;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class UserWalletApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserWalletApiApplication.class, args);
	}

}
