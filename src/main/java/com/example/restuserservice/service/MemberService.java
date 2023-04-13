//package com.example.restuserservice.service;
//
//import com.example.restuserservice.domain.UserDTO;
//import com.example.restuserservice.mapper.MemberMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MemberService {
//
//    @Autowired
//    private MemberMapper memberMapper;
//
//    @Autowired
//    private BCryptPasswordEncoder encoder;
//
//
//    public void createMember(UserDTO user) {
//        String password = user.getPassword();
//        user.setPassword(encoder.encode(password));
//        System.out.println(user);
//        memberMapper.insertMember(user);
//    }
//
//
//    public void updateMember(UserDTO updateUser) {
//        UserDTO user = memberMapper.findUser(updateUser.getId());
//
//        if (user != null) {
//            memberMapper.updateMember(user);
//        } else {
//            throw new IllegalStateException("회원정보가 존재하지 않습니다.");
//        }
//    }
//
//
//    public void deleteMember(String id) {
//        if (memberMapper.findUser(id) != null) {
//            memberMapper.deleteMember(id);
//        } else {
//            throw new IllegalStateException("회원정보가 존재하지 않습니다.");
//        }
//    }
//
//
//    public boolean checkPw(UserDTO user) {
//        return false;
//    }
//}
