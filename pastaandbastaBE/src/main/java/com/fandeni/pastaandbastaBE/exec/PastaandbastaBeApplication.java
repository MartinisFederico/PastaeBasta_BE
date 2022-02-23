package com.fandeni.pastaandbastaBE.exec;

import com.fandeni.pastaandbastaBE.model.Ruolo;
import com.fandeni.pastaandbastaBE.service.InitializationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;


@SpringBootApplication(scanBasePackages = {"com.fandeni.pastaandbastaBE.service", "com.fandeni.pastaandbastaBE.controller", "com.fandeni.pastaandbastaBE.conf"},
	exclude = SecurityAutoConfiguration.class)
@EnableJpaRepositories(basePackages =  {"com.fandeni.pastaandbastaBE.repository"})
@EntityScan(basePackages = {"com.fandeni.pastaandbastaBE.model"})
public class PastaandbastaBeApplication {
	public static void main(String[] args) {
		try {
			ConfigurableApplicationContext app = SpringApplication.run(PastaandbastaBeApplication.class, args);
			((InitializationService)app.getBean("initializationService"))
					.insertRole(List.of((Ruolo)app.getBean("ruoloAdmin"), (Ruolo)app.getBean("ruoloUser")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
