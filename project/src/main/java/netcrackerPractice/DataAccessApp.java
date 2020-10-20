package netcrackerPractice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DataAccessApp {
    private static final Logger log = LoggerFactory.getLogger(DataAccessApp.class);

    public static void main(String[] args) {
        SpringApplication.run(DataAccessApp.class, args);
    }

    @Bean
    public CommandLineRunner demo(AccountRepository repository) {
        return (args) -> {
            log.info("Accounts found with findAll():");
            for (Account account : repository.findAll()) {
                log.info(account.toString());
            }
            log.info("-------------------------------\n");

            log.info("Account found with findByUsername(\"Anna\"):");
            log.info(repository.findByUsername("Anna").toString());
            log.info("--------------------------------");
        };
    }
}
