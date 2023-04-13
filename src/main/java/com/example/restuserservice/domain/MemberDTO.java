package com.example.restuserservice.domain;

import lombok.*;

import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

    private Long memNo;

    //UserDTO user;
    private String id;          //ID
    private String password;    //PW
    private String name;
    private String phone;
    private String auth;
    private Date joinDate;
    //private Long enabled;
    //private List<AuthVO> auth;
}
