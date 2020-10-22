package netcrackerPractice.controller;

import Dto.AccountDto;
import netcrackerPractice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(value = "/addUser")
    public ResponseEntity<?> signUpAccount(@RequestParam("email") String email,
                                           @RequestParam("password") String password,
                                           @RequestParam("username") String username){
        try {
            return ResponseEntity.ok(accountService.addAccount(email, username, password));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
        }

    }

}
