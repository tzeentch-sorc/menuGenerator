package edu.netcracker.menugenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MenuGenerator {

	private static final Logger log = LoggerFactory.getLogger(MenuGenerator.class);

	public static void main(String[] args) {
		SpringApplication.run(MenuGenerator.class, args);
	}

//	@Bean
//	public CommandLineRunner demo(MealRepository repository, UserRepository repository2) {
//		return (args) -> {
//            log.info("-------------------------------\n");
//            Long id = 5L;
//			Optional<Meal> meal = repository.findByName("Нежный хек");
//			log.info(meal.toString());
//			log.info("-------------------------------\n");
//			Optional<Meal> mealById = repository.findById(5L);
//			log.info(mealById.toString());
////			log.info("-------------------------------\n");
////			Slice<Meal> meals = repository.findAllByType(MealType.TYPE_BREAKFAST);
////			log.info(meals.toString());
//			//Optional<User> user = repository2.findByUsername("reign");
//			//log.info(user.toString());
//	};
//	}
}
