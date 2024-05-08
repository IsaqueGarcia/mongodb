package com.isaque.mongodb.controller;

import com.isaque.mongodb.dto.*;
import com.isaque.mongodb.repository.UsersRepository;
import com.isaque.mongodb.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsersRepository repository;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO dto) {
        var usernameAuthentication = new UsernamePasswordAuthenticationToken(dto.getLogin(), dto.getPassword());
        var auth = authenticationManager.authenticate(usernameAuthentication);
        var token = tokenService.generateToken((Users) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO register){
        if(repository.findByLogin(register.getLogin()) != null) return ResponseEntity.badRequest().build();

        String passwordEncoder = new BCryptPasswordEncoder().encode(register.getPassword());

        Users user = new Users();
        user.setLogin(register.getLogin());
        user.setPassword(passwordEncoder);
        user.setRole(register.getRole());

        repository.save(user);

        return ResponseEntity.ok().build();
    }

}
