package netcrackerPractice.service;

import netcrackerPractice.entity.Account;

public interface AccountService {
    Account getByEmail(String email);

    Account addAccount(String email, String username, String password) throws Exception;

}
