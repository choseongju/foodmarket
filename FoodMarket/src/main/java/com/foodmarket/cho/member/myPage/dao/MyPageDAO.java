package com.foodmarket.cho.member.myPage.dao;

import java.util.List;

import com.foodmarket.cho.security.domain.UserDTO;

public interface MyPageDAO {
	
	public List<UserDTO> memberInfo(String mb_id) throws Exception;

}
