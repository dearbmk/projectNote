package com.example.projectnote.config;

import com.example.projectnote.data.dao.UserDAO;
import com.example.projectnote.data.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
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



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        UserEntity user = userDAO.getUser(username);


        if (user == null) {
            throw new UsernameNotFoundException("User doesnt exist.");
        }

        String pwd = user.getPassword();
        String roles = user.getRoles();

        return User.builder()
                .username(username)
                .password(encoder.encode(pwd))
                .roles(roles)
                .build();

    }
}

