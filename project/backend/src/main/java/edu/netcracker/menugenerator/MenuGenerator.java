package edu.netcracker.menugenerator;

import edu.netcracker.menugenerator.entity.Meal;
import edu.netcracker.menugenerator.repository.MealRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@SpringBootApplication
public class MenuGenerator {

	private static final Logger log = LoggerFactory.getLogger(MenuGenerator.class);

	public static void main(String[] args) {
		SpringApplication.run(MenuGenerator.class, args);
	}

	@Bean
	public CommandLineRunner demo(MealRepository repository) {
		return (args) -> {

//            Long id = 5L;
//            log.info("-------------------------------\n");
//			Optional<Meal> mealById = repository.findById(5L);
//			log.info(mealById.get().getMealProducts().toString());
//			log.info("-------------------------------\n");
//			Slice<Meal> meals = repository.findAllByType(MealType.TYPE_BREAKFAST);
//			log.info(meals.toString());
			//Optional<User> user = repository2.findByUsername("reign");
			//log.info(user.toString());
	};
	}
}
