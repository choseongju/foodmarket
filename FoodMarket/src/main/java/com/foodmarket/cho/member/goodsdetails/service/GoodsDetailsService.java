package com.foodmarket.cho.member.goodsdetails.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodmarket.cho.member.goodsdetails.domain.dao.GoodsDetailsDAO;
import com.foodmarket.cho.owner.domain.GoodsDTO;

@Service
public class GoodsDetailsService {
	
	@Autowired
	GoodsDetailsDAO dao;
	
	public List<GoodsDTO> goodsDetails(String goods_uid){
		return dao.goodsDetails(goods_uid);
	}
	
	public int updateViewCount(String goods_uid) {
		return dao.updateViewCount(goods_uid);
	}
	
	public int selectGoodsLike(Map<String, Object> map) {
		return dao.selectGoodsLike(map);
	}
	 
	 public void insertGoodsLike(Map<String, Object> map) {
		dao.insertGoodsLike(map);
	}

	public void deleteGoodsLike(Map<String, Object> map) {
		dao.deleteGoodsLike(map);
	}
	
	public void updateGoodsLike(String goods_uid) {
		dao.updateGoodsLike(goods_uid);
	}
	
	public int cancelGoodsLike(String goods_uid) {
		return dao.cancelGoodsLike(goods_uid);
	}
}
