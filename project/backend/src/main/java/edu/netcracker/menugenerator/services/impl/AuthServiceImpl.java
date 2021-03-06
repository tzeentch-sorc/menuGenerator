package edu.netcracker.menugenerator.services.impl;

import edu.netcracker.menugenerator.dto.request.LoginRequest;
import edu.netcracker.menugenerator.dto.request.SignupRequest;
import edu.netcracker.menugenerator.dto.response.JwtResponse;
import edu.netcracker.menugenerator.dto.response.MessageResponse;
import edu.netcracker.menugenerator.entity.Profile;
import edu.netcracker.menugenerator.entity.Role;
import edu.netcracker.menugenerator.entity.User;
import edu.netcracker.menugenerator.entity.UserDetailsImpl;
import edu.netcracker.menugenerator.repository.ProfileRepository;
import edu.netcracker.menugenerator.repository.RoleRepository;
import edu.netcracker.menugenerator.repository.UserRepository;
import edu.netcracker.menugenerator.security.jwt.JwtUtils;
import edu.netcracker.menugenerator.services.AuthService;
import edu.netcracker.menugenerator.util.ERole;
import edu.netcracker.menugenerator.util.exceptions.EmailTakenException;
import edu.netcracker.menugenerator.util.exceptions.UsernameTakenException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class AuthServiceImpl implements AuthService {

    UserRepository userRepository;
    ProfileRepository profileRepository;
    AuthenticationManager authenticationManager;
    RoleRepository roleRepository;
    PasswordEncoder encoder;
    JwtUtils jwtUtils;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager,
            RoleRepository roleRepository, PasswordEncoder encoder, JwtUtils jwtUtils, ProfileRepository profileRepository){
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.profileRepository = profileRepository;
    }

    @Override
    public JwtResponse signin(LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }

    @Override
    public MessageResponse signup(SignupRequest signupRequest){
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            throw new UsernameTakenException("Registration fail: Username taken");
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new EmailTakenException("Registration fail: Email taken");
        }

        User user = new User(signupRequest.getUsername(),
                signupRequest.getEmail(),
                encoder.encode(signupRequest.getPassword()));

        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                Role temporaryRole = roleRepository.findByName(ERole.valueOf(role))
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(temporaryRole);
            });
        }

        user.setRoles(roles);
        Profile profile = new Profile();

        user.setProfile(profile);
        profile.setUser(user);

        userRepository.save(user);
        return new MessageResponse("User registered successfully!");
    }
}
