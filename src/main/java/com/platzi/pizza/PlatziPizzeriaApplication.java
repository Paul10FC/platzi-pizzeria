package com.platzi.pizza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
// habilitar y configurar la funcionalidad de repositorios JPA en tu aplicación.
@EnableJpaRepositories
@EnableJpaAuditing
public class PlatziPizzeriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlatziPizzeriaApplication.class, args);
	}

}
