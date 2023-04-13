//package com.example.restuserservice.mapper;
//
//import com.example.restuserservice.domain.UserDTO;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//
//import java.util.List;
//
//@Mapper
//public interface MemberMapper {
//
//    public List<UserDTO> findAllUsers();
//
//    public UserDTO findUser(@Param("id") String id);
//
//    //회원가입
//    public void insertMember(UserDTO user);
//
//    //로그인
//    public UserDTO loginMember(UserDTO user);
//
//    //로그인 pw 조회
//    public String readPw(@Param("id") String id);
//
//    //회원정보 변경
//    public void updateMember(UserDTO user);
//
//    //회원 탈퇴
//    public void deleteMember(@Param("id") String id);
//
//}
