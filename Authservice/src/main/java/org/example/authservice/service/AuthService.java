package org.example.authservice.service;

import org.example.authservice.User;
import org.example.authservice.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public String generateToken(String name, String password){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(name, password));
        if (authenticate.isAuthenticated()){
            return jwtService.generateToken(name);
        } else {
            throw new RuntimeException("Authentication failed");
        }
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
}
