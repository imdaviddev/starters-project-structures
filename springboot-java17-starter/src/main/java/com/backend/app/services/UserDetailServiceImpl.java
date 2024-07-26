package com.backend.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.backend.app.model.User;
import com.backend.app.repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("El usuario " + username  + " no fue encontrado-"));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        user.getRoles()
            .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEmun().name()))));

        user.getRoles().stream()
            .flatMap(role -> role.getPermissionList().stream())
            .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));

        
        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            user.isEnabled(),
            user.isAccountNoExpired(),
            user.isCredentialNoExpired(),
            user.isAccountNoLocked(),
            authorityList
        );
    }
    
}
