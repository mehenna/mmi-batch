package io.mmi.spring.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class InitspringbatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(InitspringbatchApplication.class, args);
	}
}
