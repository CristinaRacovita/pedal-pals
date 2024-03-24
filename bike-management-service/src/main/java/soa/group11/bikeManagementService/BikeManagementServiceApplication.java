package soa.group11.bikeManagementService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class BikeManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BikeManagementServiceApplication.class, args);
	}

}
