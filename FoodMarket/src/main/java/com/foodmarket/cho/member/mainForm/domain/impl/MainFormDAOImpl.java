package com.foodmarket.cho.member.mainForm.domain.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.foodmarket.cho.member.mainForm.domain.MainFormDAO;
import com.foodmarket.cho.owner.domain.GoodsDTO;

@Repository
public class MainFormDAOImpl implements MainFormDAO{

	private MainFormDAO mapper;
	
	@Autowired
	public MainFormDAOImpl(SqlSession sqlSession) {
		mapper = sqlSession.getMapper(MainFormDAO.class);
	}
	
	@Override
	public List<GoodsDTO> selectGoods() {
		return mapper.selectGoods();
	}

	@Override
	public List<String> viewCountTop4Goods() {
		return mapper.viewCountTop4Goods();
	}

	@Override
	public List<GoodsDTO> top4Goods(String uid) {
		return mapper.top4Goods(uid);
	}

	@Override
	public List<String> likeCountTop4Goods() {
		return mapper.likeCountTop4Goods();
	}

	@Override
	public List<GoodsDTO> likeTop4Goods(String uid) {
		return mapper.likeTop4Goods(uid);
	}

	@Override
	public List<GoodsDTO> goodsSearch(String keyword) {
		return mapper.goodsSearch(keyword);
	}

}
