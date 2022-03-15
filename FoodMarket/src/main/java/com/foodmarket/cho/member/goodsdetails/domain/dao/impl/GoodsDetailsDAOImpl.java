package com.foodmarket.cho.member.goodsdetails.domain.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.foodmarket.cho.member.goodsdetails.domain.dao.GoodsDetailsDAO;
import com.foodmarket.cho.owner.domain.GoodsDTO;

@Repository
public class GoodsDetailsDAOImpl implements GoodsDetailsDAO {

	private GoodsDetailsDAO mapper;

	@Autowired
	public GoodsDetailsDAOImpl(SqlSession sqlSession) {
		mapper = sqlSession.getMapper(GoodsDetailsDAO.class);
	}

	@Override
	public List<GoodsDTO> goodsDetails(String goods_uid) {
		return mapper.goodsDetails(goods_uid);
	}

	@Override
	public int updateViewCount(String goods_uid) {
		return mapper.updateViewCount(goods_uid);
	}

	@Override
	public int selectGoodsLike(Map<String, Object> map) {
		return mapper.selectGoodsLike(map);
	}

	 @Override 
	 public void insertGoodsLike(Map<String, Object> map) { 
		 mapper.insertGoodsLike(map);
	 }

	@Override
	public void deleteGoodsLike(Map<String, Object> map) {
		mapper.deleteGoodsLike(map);
	}

	@Override
	public int updateGoodsLike(String goods_uid) {
		return mapper.updateGoodsLike(goods_uid);
	}

	@Override
	public int cancelGoodsLike(String goods_uid) {
		return mapper.cancelGoodsLike(goods_uid);
	}

}
