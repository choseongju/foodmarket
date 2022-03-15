package com.foodmarket.cho.member.mainForm.domain;

import java.util.List;

import com.foodmarket.cho.owner.domain.GoodsDTO;

public interface MainFormDAO {
	
	public List<GoodsDTO> selectGoods();
	
	public List<String> viewCountTop4Goods();
	
	public List<GoodsDTO> top4Goods(String uid);
	
	public List<String> likeCountTop4Goods();
	
	public List<GoodsDTO> likeTop4Goods(String uid);
	
	public List<GoodsDTO> goodsSearch(String keyword);
}
