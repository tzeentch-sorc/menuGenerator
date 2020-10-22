package netcrackerPractice;

import netcrackerPractice.entity.Account;
import netcrackerPractice.repository.AccountRepository;
import netcrackerPractice.service.AccountService;
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
    public CommandLineRunner demo(AccountRepository repository, AccountService accountService) {
        return (args) -> {
            log.info("Accounts found with findAll():");
            for (Account account : repository.findAll()) {
                log.info(account.toString());
            }
            log.info("-------------------------------\n");

            log.info("Account found with findByUsername(\"Anna\"):");
            log.info(repository.findByUsername("Anna").toString());
            log.info("================================");
            //log.info(accountService.addAccount("qwerty", "test", "1234").toString());
        };
    }
}
