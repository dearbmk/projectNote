package com.example.projectnote.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserDto {

    @NotNull
    private String username;

    @NotNull
    private String userPassword;

    @Email
    private String userEmail;

}
