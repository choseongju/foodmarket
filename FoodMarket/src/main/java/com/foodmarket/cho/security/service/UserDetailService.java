package com.foodmarket.cho.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foodmarket.cho.security.domain.UserDAO;
import com.foodmarket.cho.security.domain.UserDTO;

@Service
public class UserDetailService {

	@Autowired
	UserDAO dao;
	
	// 회원가입
	// ROLE_MEMBER 권한 부여
	@Transactional
	public int addMember(UserDTO user) {
		int cnt = dao.addUser(user);
		System.out.println(cnt);
		dao.addAuth(user.getId(), "ROLE_MEMBER");
		return cnt;
	}
	
	// 회원삭제
	@Transactional
	public int deleteMember(UserDTO user) {
		dao.deleteAuths(user.getId()); // 권한들 먼저 삭제
		int cnt = dao.deleteUser(user); // 회원 삭제
		return cnt;
	}
	
	// 특정 id(username) 의 정보 가져오기
	public UserDTO findById(String id) {
		return dao.findById(id);
	}
	
	// 특정 id의 권한(들) 정보 가져오기
	public List<String> selectAuthoritiesById(String id){
		return dao.selectAuthorityById(id);
	}
	
	public int checkID(String id) {
		int cnt = dao.checkID(id);
		return cnt;
	}
	
	public int checkEmail(String email) {
		int cnt = dao.checkEmail(email);
		return cnt;
	}
	
	public String selectUserName(String id) {
		return dao.selectUserName(id);
	}
}





















