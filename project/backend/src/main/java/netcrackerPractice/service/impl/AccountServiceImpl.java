package netcrackerPractice.service.impl;

import netcrackerPractice.entity.Account;
import netcrackerPractice.repository.AccountRepository;
import netcrackerPractice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private String salt = BCrypt.gensalt();

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account getByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public Account addAccount(String username, String password, String email) throws Exception {
        if (accountRepository.existsByEmail(email)) {
            throw new Exception("Account with email \" " + email + " \" already exist");
        } else {
            Account account = new Account(username, BCrypt.hashpw(password, salt), email);
            accountRepository.save(account);
            return account;
        }
    }
}
