package com.project.back_login.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user")
    public ResponseEntity user() {
        System.out.println("rodando");
        return ResponseEntity.ok("Sucesso");
    }


    @GetMapping("/teste")
    public String teste() {
        System.out.println("CHEGOU NO CONTROLLER");
        return "Funcionando";
    }

}
