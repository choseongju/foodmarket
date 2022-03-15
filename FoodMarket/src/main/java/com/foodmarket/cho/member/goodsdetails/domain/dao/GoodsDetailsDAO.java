package com.foodmarket.cho.member.goodsdetails.domain.dao;

import java.util.List;
import java.util.Map;

import com.foodmarket.cho.owner.domain.GoodsDTO;

public interface GoodsDetailsDAO {
	
	public List<GoodsDTO> goodsDetails(String goods_uid);
	
	public int updateViewCount(String goods_uid);
	
	public int selectGoodsLike(Map<String, Object> map);
	
	public void insertGoodsLike(Map<String, Object> map);
	
	public void deleteGoodsLike(Map<String, Object> map);
	
	public int updateGoodsLike(String goods_uid);
	
	public int cancelGoodsLike(String goods_uid);
}