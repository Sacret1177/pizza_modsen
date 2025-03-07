package org.example.authservice.config;

import org.example.authservice.User;
import org.example.authservice.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        Optional<User> credentials =  repository.findByLogin(login);
        return credentials.map(CustomUserDetails::new).orElseThrow(()-> new UsernameNotFoundException("User not found with login " + login));
    }
}