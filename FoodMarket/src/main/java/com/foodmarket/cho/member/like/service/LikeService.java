package com.foodmarket.cho.member.like.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodmarket.cho.member.like.dao.LikeDAO;
import com.foodmarket.cho.owner.domain.GoodsDTO;

@Service
public class LikeService {
	
	@Autowired
	LikeDAO dao;
	
	public List<String> selectGoodsUid(String mb_id) throws Exception{
		return dao.selectGoodsUid(mb_id);
	}
	
	public List<GoodsDTO> selectGoods(String goods_uid) throws Exception{
		return dao.selectGoods(goods_uid);
	}
	
	public void deleteLike(String goods_uid) throws Exception{
		dao.deleteLike(goods_uid);
	}
}
