package com.csvfile.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication (scanBasePackages = {"com.csvfile.api"})
public class CsvFileApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsvFileApiApplication.class, args);
	}

}
