package com.it355.projekat;

import com.it355.projekat.beanannotation.Exclude;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.it355.projekat")
@EntityScan(basePackages = "com.it355.projekat")
@ConfigurationPropertiesScan("com.it355.projekat")
@EnableJpaRepositories(value = "com.it355.projekat",
		excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Exclude.class))
public class IT355ApplicationPZ {
	public static void main(String[] args) {
		SpringApplication.run(IT355ApplicationPZ.class, args);
	}
}
