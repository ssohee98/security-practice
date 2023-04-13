package com.example.restuserservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminVO {
    //private String id;
    //private String password;
    UserDTO user;
    private String name;
    //private List<AuthVO> auth;
}
