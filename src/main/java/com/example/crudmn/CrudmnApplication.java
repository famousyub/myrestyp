package com.example.crudmn;

import com.example.crudmn.config.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.annotation.PostConstruct;
import java.util.TimeZone;



@EntityScan(basePackageClasses = {
		CrudmnApplication.class,
		Jsr310JpaConverters.class
})

@EnableMongoRepositories
@EnableJpaRepositories
@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class CrudmnApplication  {

	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	public static void main(String[] args) {
		SpringApplication.run(CrudmnApplication.class, args);
	}

}
