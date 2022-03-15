package com.foodmarket.cho.member.myPage.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.foodmarket.cho.member.myPage.dao.MyPageDAO;
import com.foodmarket.cho.security.domain.UserDTO;

@Repository
public class MyPageDAOImpl implements MyPageDAO{
	
	private MyPageDAO mapper;
	
	@Autowired
	public MyPageDAOImpl(SqlSession sqlSession) {
		mapper = sqlSession.getMapper(MyPageDAO.class);
	}
	
	@Override
	public List<UserDTO> memberInfo(String mb_id) throws Exception {
		return mapper.memberInfo(mb_id);
	}

}
