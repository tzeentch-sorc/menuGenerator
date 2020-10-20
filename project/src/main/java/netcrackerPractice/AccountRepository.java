package netcrackerPractice;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface AccountRepository extends CrudRepository<Account, Long> {

    Account findByUsername(@Param("username") String username);
}

