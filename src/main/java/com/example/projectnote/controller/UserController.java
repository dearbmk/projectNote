package com.example.projectnote.controller;

import com.example.projectnote.service.UserService;
import com.example.projectnote.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    private UserService userService;
    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/userInfo/{username}")
    public String myAccount(@PathVariable String username) {

        long startTime = System.currentTimeMillis();
        LOGGER.info("[UserController perform {} of projectnote API", "myAccount");
        UserDto userDto = userService.getUser(username);

        LOGGER.info("[UserController] response :: username = {}, userPwd = {}, userEmail = {}, Response Time = {}ms",
                userDto.getUsername(), userDto.getUserPassword(), userDto.getUserEmail(), (System.currentTimeMillis()-startTime));
        return userDto.toString();
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String email) {

        UserDto userDto = userService.saveUser(username, password, email);
        return userDto.toString();
    }

    @PostMapping("registration")
    public String registration(@Valid @RequestBody UserDto userDto) { //JSON type information
        return userService.saveUser(userDto.getUsername(), userDto.getUserPassword(), userDto.getUserEmail()).toString();
    }

    @DeleteMapping(value = "userInfo/delete/{username}")
    public String deleteAccount(@PathVariable String username) {
        userService.deleteUser(username);
        return "home";
    }

    @PostMapping("log-test")
    public void logTest() {

        LOGGER.trace("Trace Log");
        LOGGER.debug("Debug Log");
        LOGGER.info("Info Log");
        LOGGER.warn("Warn Log");
        LOGGER.error("Error Log");
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Map<String, String>> ExceptionHandler(Exception e){
        HttpHeaders responseHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        LOGGER.info(e.getLocalizedMessage());
        LOGGER.info("Call ExceptionHandler in the Controller");

        Map<String,String> map = new HashMap<>();
        map.put("error type", httpStatus.getReasonPhrase());
        map.put("code", "400");
        map.put("message", "Error");

        return new ResponseEntity<>(map, responseHeaders, httpStatus);
    }



}
