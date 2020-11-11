package edu.netcracker.menugenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import edu.netcracker.menugenerator.repository.MealRepository;
import edu.netcracker.menugenerator.entity.Meal;

import java.util.Optional;

@SpringBootApplication
public class MenuGenerator {

	private static final Logger log = LoggerFactory.getLogger(MenuGenerator.class);

	public static void main(String[] args) {
		SpringApplication.run(MenuGenerator.class, args);
	}

//	@Bean
//	public CommandLineRunner demo(MealRepository repository) {
//		return (args) -> {
//            log.info("Accounts found with findAll():");
//            for (Meal meal : repository.findAll()) {
//                log.info(meal.toString());
//            }
//            log.info("-------------------------------\n");
//            Long id = 5L;
//			//Meal meal = repository.findByName("Нежный хек");
//			//log.info(meal.toString());
//			log.info("-------------------------------\n");
//			Optional<Meal> mealById = repository.findById(5L);
//			log.info(mealById.get().toString());
//			log.info("-------------------------------\n");
//		};
//	}
}
