package com.foodmarket.cho.member.like.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.foodmarket.cho.member.like.dao.LikeDAO;
import com.foodmarket.cho.owner.domain.GoodsDTO;

@Repository
public class LikeDAOImpl implements LikeDAO{

	private LikeDAO mapper;
	
	@Autowired
	public LikeDAOImpl(SqlSession sqlSession) {
		mapper = sqlSession.getMapper(LikeDAO.class);
	}
	
	@Override
	public List<String> selectGoodsUid(String mb_id) throws Exception {
		return mapper.selectGoodsUid(mb_id);
	}

	@Override
	public List<GoodsDTO> selectGoods(String goods_uid) throws Exception {
		return mapper.selectGoods(goods_uid);
	}

	@Override
	public void deleteLike(String goods_uid) throws Exception {
		mapper.deleteLike(goods_uid);
		
	}

}
