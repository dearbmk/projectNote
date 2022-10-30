package com.example.projectnote.data.dao.impl;

import com.example.projectnote.data.dao.UserDAO;
import com.example.projectnote.data.entity.UserEntity;
import com.example.projectnote.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDAOImpl implements UserDAO {

    UserRepository userRepository;

    @Autowired
    public UserDAOImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity saveUser(UserEntity userEntity) {
        userRepository.save(userEntity);
        return userEntity;
    }

    @Override
    public UserEntity getUser(String username) {
        UserEntity userEntity = userRepository.getById(username);
        return userEntity;
    }
}
