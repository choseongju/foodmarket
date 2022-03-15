package com.foodmarket.cho.member.like.dao;

import java.util.List;

import com.foodmarket.cho.owner.domain.GoodsDTO;

public interface LikeDAO {

	public List<String> selectGoodsUid(String mb_id) throws Exception;
	
	public List<GoodsDTO> selectGoods(String goods_uid) throws Exception;
	
	public void deleteLike(String goods_uid) throws Exception;
}
