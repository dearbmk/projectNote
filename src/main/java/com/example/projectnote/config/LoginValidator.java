package com.example.projectnote.config;

import com.example.projectnote.data.dao.UserDAO;
import com.example.projectnote.data.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginValidator implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userDAO.getUser(username);
        if (user == null){
            return null;
        }

        String pwd = user.getPassword();

        return User.builder()
                .username(username)
                .password(pwd)
                .build();
    }
}

