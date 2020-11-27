package edu.netcracker.menugenerator.services;

import edu.netcracker.menugenerator.dto.request.LoginRequest;
import edu.netcracker.menugenerator.dto.request.SignupRequest;
import edu.netcracker.menugenerator.dto.response.JwtResponse;
import edu.netcracker.menugenerator.dto.response.MessageResponse;



public interface AuthService {
    JwtResponse signin(LoginRequest loginRequest);
    MessageResponse signup(SignupRequest signupRequest);
}
