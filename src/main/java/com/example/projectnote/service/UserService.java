package com.example.projectnote.service;

import com.example.projectnote.dto.UserDto;

public interface UserService {

    UserDto saveUser(String username, String password, String email, String roles);

    UserDto getUser(String username);

    boolean deleteUser(String username);
}
