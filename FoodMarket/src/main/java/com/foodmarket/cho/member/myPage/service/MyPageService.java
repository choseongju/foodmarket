package com.foodmarket.cho.member.myPage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodmarket.cho.member.myPage.dao.MyPageDAO;
import com.foodmarket.cho.security.domain.UserDTO;

@Service
public class MyPageService {

	@Autowired
	MyPageDAO dao;
	
	public List<UserDTO> memberInfo(String mb_id) throws Exception{
		return dao.memberInfo(mb_id);
	}
}
