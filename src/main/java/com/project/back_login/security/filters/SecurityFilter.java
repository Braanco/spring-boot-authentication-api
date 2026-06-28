package com.project.back_login.security.filters;

import com.project.back_login.repository.UserRepository;
import com.project.back_login.security.token.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository repository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = this.recoverToken(request);
        String login = tokenService.validateToken(token);

        if (login != null){
            var user = repository.findByEmail(login).orElseThrow(() -> new RuntimeException("user not found "));
            var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
            var authorization = new UsernamePasswordAuthenticationToken(user, null , authorities);
            SecurityContextHolder.getContext().setAuthentication(authorization);
        }

    }

    private String recoverToken(HttpServletRequest request) {
        String authoHeader = request.getHeader("Authorization");
        if (authoHeader == null) {
            return null;
        }
        return authoHeader.replace("Bearer", "");
    }
}
