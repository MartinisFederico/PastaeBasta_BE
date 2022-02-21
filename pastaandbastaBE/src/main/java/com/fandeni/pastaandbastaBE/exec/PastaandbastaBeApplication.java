package com.fandeni.pastaandbastaBE.exec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = {"com.fandeni.pastaandbastaBE.service", "com.fandeni.pastaandbastaBE.controller"})
@EnableJpaRepositories(basePackages =  {"com.fandeni.pastaandbastaBE.repository"})
@EntityScan(basePackages = {"com.fandeni.pastaandbastaBE.model"})
public class PastaandbastaBeApplication {
	public static void main(String[] args) {
		try {
			ConfigurableApplicationContext app = SpringApplication.run(PastaandbastaBeApplication.class, args);;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
