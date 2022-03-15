package com.foodmarket.cho.member.mainForm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodmarket.cho.member.mainForm.domain.MainFormDAO;
import com.foodmarket.cho.owner.domain.GoodsDTO;

@Service
public class MainFormService {

	@Autowired
	MainFormDAO dao;
	
	public List<GoodsDTO> selectGoods(){
		return dao.selectGoods();
	}
	
	public List<String> viewCountTop4Goods(){
		return dao.viewCountTop4Goods();
	}
	
	public List<GoodsDTO> top4Goods(String uid){
		return dao.top4Goods(uid);
	}
	
	public List<String> likeCountTop4Goods(){
		return dao.likeCountTop4Goods();
	}
	
	public List<GoodsDTO> likeTop4Goods(String uid){
		return dao.likeTop4Goods(uid);
	}
	
	public List<GoodsDTO> goodsSearch(String keyword){
		return dao.goodsSearch(keyword);
	}
}