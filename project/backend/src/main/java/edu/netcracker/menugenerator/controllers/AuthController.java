package edu.netcracker.menugenerator.controllers;

import java.util.Arrays;

import javax.validation.Valid;

import edu.netcracker.menugenerator.services.impl.AuthServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.netcracker.menugenerator.dto.request.LoginRequest;
import edu.netcracker.menugenerator.dto.request.SignupRequest;
import edu.netcracker.menugenerator.dto.response.MessageResponse;


@CrossOrigin(origins = {"${menuGenerator.cors.allowedOrigin}"}, maxAge = 3600)
@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

	AuthServiceImpl authService;

	@Autowired
	public AuthController(AuthServiceImpl authService){
		this.authService = authService;
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
		return ResponseEntity.ok(authService.signin(loginRequest));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
		try {
			MessageResponse msg = authService.signup(signUpRequest);
			if(msg.isOk()){
				return ResponseEntity.ok(msg);
			} else return ResponseEntity.badRequest().body(msg);
		}
		catch (Exception e){
			log.info(Arrays.toString(e.getStackTrace()));
			return ResponseEntity.badRequest().body(new MessageResponse("Critical fail", false));
		}
	}
}
