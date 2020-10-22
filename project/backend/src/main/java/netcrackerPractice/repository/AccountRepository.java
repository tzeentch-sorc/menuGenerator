package netcrackerPractice.repository;

import netcrackerPractice.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    Account findByUsername(@Param("username") String username);

    Account findByEmail(String email);

    boolean existsByEmail(String email);
}

