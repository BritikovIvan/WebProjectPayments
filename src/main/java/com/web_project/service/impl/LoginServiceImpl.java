package com.web_project.service.impl;

import com.web_project.exception.UserNotFoundException;
import com.web_project.model.entity.Login;
import com.web_project.model.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements UserDetailsService {
    private final LoginRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userOptional = repository.findByLogin(username);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException(username);
        }
        var user = userOptional.get();
        return new User(user.getUser().getId().toString(), user.getPassword(), user.isEnabled(), user.isAccountNonExpired(),
                user.isCredentialsNonExpired(), user.isAccountNonLocked(), getAuthorities(user));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Login user) {
        return Collections.singleton(new SimpleGrantedAuthority(user.getUser().getRole().getRole()));
    }
}
