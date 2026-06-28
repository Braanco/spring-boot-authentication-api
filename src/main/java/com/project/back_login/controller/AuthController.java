package com.project.back_login.controller;

import com.project.back_login.domain.users.User;
import com.project.back_login.dtos.ResponseDTO;
import com.project.back_login.dtos.UserLoginRequesDto;
import com.project.back_login.dtos.UserRegisterRequestDto;
import com.project.back_login.repository.UserRepository;
import com.project.back_login.security.token.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository REPOSITORY;
    private final TokenService TOKEN_SERVICE;
    private final PasswordEncoder PASSWORD_ENCODER;

    public AuthController(UserRepository repository, TokenService tokenservice, PasswordEncoder passwordencoder) {
        REPOSITORY = repository;
        TOKEN_SERVICE = tokenservice;
        PASSWORD_ENCODER = passwordencoder;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLoginRequesDto userdto) {
        User byUser = this.REPOSITORY.findByEmail(userdto.email()).orElseThrow(() -> new RuntimeException("User not found"));

        if (PASSWORD_ENCODER.matches(userdto.password(), byUser.getPassWord())) {
            String token = this.TOKEN_SERVICE.genereteToken(byUser);
            return ResponseEntity.ok(new ResponseDTO(byUser.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserRegisterRequestDto userdto) {
        System.out.println("ta rodando");
        Optional<User> byUser = this.REPOSITORY.findByEmail(userdto.email());
        if (byUser.isEmpty()) {
            User newUser = new User();
            newUser.setPassWord(PASSWORD_ENCODER.encode(userdto.password()));
            newUser.setName(userdto.name());
            newUser.setEmail(userdto.email());
            this.REPOSITORY.save(newUser);

            String token = this.TOKEN_SERVICE.genereteToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(userdto.name(), token));

        }

        return ResponseEntity.badRequest().build();
    }
}
