package com.foodmarket.cho.security.domain;

import java.util.List;

public interface UserDAO {
	// 사용자 추가
	int addUser(UserDTO user);
	
	// 특정 id(username) 사용자에 권한 추가
	int addAuth(String id, String auth);
	
	// 사용자 삭제
	int deleteUser(UserDTO user);
	
	// 특정 id(username) 사용자 의 특정 권한 삭제
	int deleteAuth(String id, String auth);
	
	// 특정 id(username) 사용자 의 권한(들) 전부 삭제
	int deleteAuths(String id);
	
	// 특정 id(username) 사용자 조회
	UserDTO findById(String id);
	
	// 특정 id(username) 사용자 의 권한(들) 뽑기
	List<String> selectAuthorityById(String id);
	
	int checkID(String id);
	
	int checkEmail(String email);
	
	String selectUserName(String id);
}





















