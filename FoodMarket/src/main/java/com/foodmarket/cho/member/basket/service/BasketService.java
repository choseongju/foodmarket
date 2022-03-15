package com.foodmarket.cho.member.basket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodmarket.cho.member.basket.domain.dao.BasketDAO;
import com.foodmarket.cho.member.basket.domain.dto.BasketDTO;

@Service
public class BasketService {
	
	@Autowired
	BasketDAO dao;
	
	public void insertBakset(BasketDTO basketDTO) throws Exception{
		dao.insertBasket(basketDTO);
	}
	
	public List<String> selectGoodsId(String mb_id) throws Exception{
		return dao.selectGoodsId(mb_id);
	}
	
	public String selectFilePath(String goods_uid) throws Exception{
		return dao.selectFilePath(goods_uid);
	}
	
	public List<BasketDTO> selectBasket(String mb_id) throws Exception{
		return dao.selectBasket(mb_id);
	}
	
	public void deleteBasket(String basket_uid) throws Exception{
		dao.deleteBasket(basket_uid);
	}
	
	public int selectGoods(BasketDTO basketDTO) throws Exception{
		return dao.selectGoods(basketDTO);
	}
	
	public void updateGoodsQuantity(BasketDTO basketDTO) throws Exception{
		dao.updateGoodsQuantity(basketDTO);
	}
	
	public int selectOrderCntMember(String mb_id) throws Exception{
		return dao.selectOrderCntMember(mb_id);
	}
} 
