package com.project.back_login.security.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.project.back_login.domain.users.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    public String genereteToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256("1234"); //secret key tem que ser em variaveis de ambiente. Estamos usando para uso educacional

            String token = JWT.create()
                    .withIssuer("api-teste")
                    .withSubject(user.getEmail())
                    .withExpiresAt(this.expiretionToken())
                    .sign(algorithm);
            return token;
        }catch (JWTCreationException exception){
            throw new RuntimeException("Error while criate token");
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256("123");
            return JWT.require(algorithm)
                    .withIssuer("api-teste")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException exception){
            return null;
        }
    }

    private Instant expiretionToken() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
