package soa.group11.bikeManagementService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import soa.group11.bikeManagementService.repositories.BikeRepository;
import soa.group11.bikeManagementService.repositories.InMemoryBikeRepository;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class BikeManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BikeManagementServiceApplication.class, args);
	}

	@Configuration
	public class BikeRepositoryConfig {

		@Bean
		@Primary
		public BikeRepository inMemoryBikeRepository() {
			return new InMemoryBikeRepository();
		}
	}

}
