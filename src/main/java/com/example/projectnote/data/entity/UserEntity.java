package com.example.projectnote.data.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user")
public class UserEntity {

    @Id
    private String username;

    private String password;

    private String email;

    private String roles;

}
