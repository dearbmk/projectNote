package com.example.projectnote.service.impl;

import com.example.projectnote.dto.UserDto;
import com.example.projectnote.data.entity.UserEntity;
import com.example.projectnote.data.repository.UserRepository;
import com.example.projectnote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDto saveUser(String username, String password, String email, String roles) {

        UserEntity userEntity = new UserEntity(username, passwordEncoder.encode(password), email, roles);
        UserEntity saved = userRepository.save(userEntity);
        UserDto userDto = new UserDto(saved.getUsername(), saved.getPassword(), saved.getEmail(), saved.getRoles());
        return userDto;
    }

    @Override
    public UserDto getUser(String username) {
        UserEntity userEntity = userRepository.getById(username);

        UserDto userDto = new UserDto(userEntity.getUsername(), passwordEncoder.encode(userEntity.getPassword()), userEntity.getEmail(), userEntity.getRoles());

        return userDto;
    }

    @Override
    public boolean deleteUser(String username) {
        userRepository.deleteById(username);
        return true;
    }
}
