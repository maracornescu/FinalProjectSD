package com.example.TicketReservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages= {"controllers", "repository", "repository.dbmodel", "business.model", "business.service"})
@EntityScan(basePackages = {"repository.dbmodel"})
@EnableJpaRepositories("repository")
@EnableSwagger2
public class TicketReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketReservationApplication.class, args);
	}
}
