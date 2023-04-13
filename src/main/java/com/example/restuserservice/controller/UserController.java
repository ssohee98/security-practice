package com.example.restuserservice.controller;

import com.example.restuserservice.domain.UserDTO;
import com.example.restuserservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String hello() {return "hello";}

    @GetMapping("/member")
    public String member() {return "user/member";}

    @GetMapping("/admin")
    public String admin() {return "user/admin";}

//    @PostMapping("/users/signup")
//    public String createUser(@RequestBody UserDTO user) {
//
//        //JSON 형태를 자바 객체 형태로 바꿔주기 위해 @RequestBody
//        userService.createMember(user);
//        System.out.println(user);
//        return "redirect:/";
//    }

//    @GetMapping("/login")
//    public String login() {
//        return ""
//    }
    @PostMapping("/signup")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user) {
        user.setAuth("ROLE_MEMBER");
        userService.createMember(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/login")  // .loginPage("LOGIN_PAGE")에서 설정한 LOGIN_PAGE와 일치해야 함
    public String getLoginForm() {
        return "login";
    }
}
