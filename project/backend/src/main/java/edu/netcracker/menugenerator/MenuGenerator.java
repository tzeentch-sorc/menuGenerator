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
	public static void main(String[] args) {
		SpringApplication.run(MenuGenerator.class, args);
	}
}
