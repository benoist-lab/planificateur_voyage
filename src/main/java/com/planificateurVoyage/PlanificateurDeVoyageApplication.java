package com.planificateurVoyage;

import javax.swing.LookAndFeel;
import javax.swing.UIManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.planificateurVoyage.sqlite.BaseInitialiser;


import lombok.RequiredArgsConstructor;

@SpringBootApplication
public class PlanificateurDeVoyageApplication {
	

	public static void main(String[] args) {
		
		
		//SpringApplication.run(PlanificateurDeVoyageApplication.class, args);
		SpringApplicationBuilder builder = new SpringApplicationBuilder(PlanificateurDeVoyageApplication.class);
		builder.headless(false);
		ConfigurableApplicationContext context = builder.run(args);

	}

}
