package com.cm;

import java.io.File;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CertificationManagerApplication {

	public static String ROOT = "upload-dir";
	
	public static void main(String[] args) {
		SpringApplication.run(CertificationManagerApplication.class, args);
	}
	
	 @Bean
    CommandLineRunner init() {
        return (String[] args) -> {
            new File(ROOT).mkdir();
        };
    }
}
