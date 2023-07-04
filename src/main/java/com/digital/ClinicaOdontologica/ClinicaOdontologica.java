package com.digital.ClinicaOdontologica;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class ClinicaOdontologica{

	private static final Logger LOGGER = LoggerFactory.getLogger(ClinicaOdontologica.class);

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		SpringApplication.run(ClinicaOdontologica.class, args);
		LOGGER.info("Proyecto integrador está ejecutandose ...");
	}

}