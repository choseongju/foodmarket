package com.foodmarket.cho.member.category.domain.dao.Impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.foodmarket.cho.member.category.domain.dao.ByCategoryDAO;
import com.foodmarket.cho.owner.domain.GoodsDTO;

@Repository
public class ByCategoryDAOImpl implements ByCategoryDAO{
	
	private ByCategoryDAO mapper;
	
	@Autowired
	public ByCategoryDAOImpl(SqlSession sqlSession) {
		mapper = sqlSession.getMapper(ByCategoryDAO.class);
	}
	
	@Override
	public List<GoodsDTO> vegetable() throws Exception {
		return mapper.vegetable();
	}

}
