package edu.netcracker.menugenerator;

import edu.netcracker.menugenerator.entity.Profile;
import edu.netcracker.menugenerator.repository.MealRepository;
import edu.netcracker.menugenerator.repository.ProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@EnableTransactionManagement
public class MenuGenerator {

	private static final Logger log = LoggerFactory.getLogger(MenuGenerator.class);

	public static void main(String[] args) {
		SpringApplication.run(MenuGenerator.class, args);
	}
//	@Bean
//	@Transactional
//	public CommandLineRunner demo(ProfileRepository repository) {
//		return (args) -> {
////			Profile prof = repository.findFirstByUserId(1);
////			log.info("profile: {}", repository.findFirstByUserId(1));
//		};
//	}
}
