package com.example.demo1.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserInfo {

    private String name;
    private String surname;
    private String email;
    private String phoneNumber;

}
