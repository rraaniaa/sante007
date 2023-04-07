package com.iset.sante;

import com.iset.sante.entities.Evenement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SanteApplication  {

	public static void main(String[] args) {
		SpringApplication.run(SanteApplication.class, args);
	}


}
