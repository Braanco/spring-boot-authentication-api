package com.project.back_login.security.details;

import com.project.back_login.domain.users.User;
import com.project.back_login.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository repository;

    public CustomUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user  = repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User details not found"));
        return new org.springframework.security.core.userdetails.User(user.getName(),user.getPassWord(), new ArrayList<>());

    }
}
