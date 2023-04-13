package com.example.restuserservice.service;

import com.example.restuserservice.domain.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService extends UserDetailsService {

    public void createMember(UserDTO user);

    public UserDTO getUserById(String id);

    //public boolean remove(Long userid);
    //public List<UserDTO> getUserList();

    //회원가입
    //public void insertMember(UserDTO user) throws Exception;

    //public void updateMember(UserDTO user);
    //public void deleteMember(String id);
    //public boolean checkPw(UserDTO user) throws Exception;
}
