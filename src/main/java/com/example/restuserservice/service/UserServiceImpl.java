package com.example.restuserservice.service;

import com.example.restuserservice.domain.UserDTO;
import com.example.restuserservice.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public void createMember(UserDTO user) {
        String password = user.getPassword();
        user.setPassword(encoder.encode(password));
        System.out.println(user);
        userMapper.insertMember(user);
    }

    @Override
    public UserDTO getUserById(String id) {
        return userMapper.getUserById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {UserDTO user = userMapper.getUser(username);
        System.out.println(user);
        if(user == null) {
            throw new UsernameNotFoundException("id: " + username + " is not found");
        }

//        String id = user.getUsername();
//        String pw = user.getPassword();
//        String auth = user.getAuth();
//        System.out.println("id: "+id+", pw: "+pw+", auth: "+auth);

//        return User.builder()
//                .username(id)
//                .password(pw)
//                .authorities(auth)
//                .build();

        return new User(user.getUsername(), user.getPassword(), Collections.singleton(new SimpleGrantedAuthority(user.getAuth())));
    }

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
//    @Override
//    public UserDTO getUser(String id) {
//        return userMapper.getUser(id);
//    }
//
//    @Override
//    public UserDTO getUserById(String id) {
//        return userMapper.getUserById(id);
//    }
//
//    @Override
//    public boolean remove(Long userid) {
//        //return userMapper.deleteUser(userid) == 1;
//        return true;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserDTO user = userMapper.getUser(username);
//        System.out.println(user);
//        if(user == null) {
//            throw new UsernameNotFoundException("id: " + username + " is not found");
//        }
//
////        String id = user.getUsername();
////        String pw = user.getPassword();
////        String auth = user.getAuth();
////        System.out.println("id: "+id+", pw: "+pw+", auth: "+auth);
//
////        return User.builder()
////                .username(id)
////                .password(pw)
////                .authorities(auth)
////                .build();
//
//        return new User(user.getUsername(), user.getPassword(), Collections.singleton(new SimpleGrantedAuthority(user.getAuth())));
//    }
}
