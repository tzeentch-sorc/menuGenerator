package netcrackerPractice.controller;

import netcrackerPractice.Dto.AccountDto;
import netcrackerPractice.service.AccountService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    private final AccountService accountService;
    private final Mapper mapper;

    @Autowired
    public AccountController(AccountService accountService, Mapper mapper) {
        this.mapper = mapper;
        this.accountService = accountService;
    }

    @PostMapping(value = "/addUser")
    public ResponseEntity<?> signUpAccount(@RequestParam("email") String email,
                                           @RequestParam("password") String password,
                                           @RequestParam("username") String username){
        try {
            return ResponseEntity.ok(mapper.map(accountService.addAccount(username, password, email), AccountDto.class));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
        }

    }

}
