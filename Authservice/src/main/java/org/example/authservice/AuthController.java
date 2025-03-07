package org.example.authservice;

import org.example.authservice.dto.AuthRequest;
import org.example.authservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService service;

    @PostMapping("/register")
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        return new ResponseEntity<>(service.saveUser(user), HttpStatus.OK);
    }

    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest request){
        return service.generateToken(request.getLogin(), request.getPassword());
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token){
        service.validateToken(token);
        return "Token is valid!";
    }
}
