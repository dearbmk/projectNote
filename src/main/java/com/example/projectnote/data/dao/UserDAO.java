package com.example.projectnote.data.dao;

import com.example.projectnote.data.entity.UserEntity;

public interface UserDAO {

    UserEntity saveUser(UserEntity userEntity);
    UserEntity getUser(String username);
}
