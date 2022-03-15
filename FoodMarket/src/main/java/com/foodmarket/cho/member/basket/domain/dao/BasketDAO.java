package com.foodmarket.cho.member.basket.domain.dao;

import java.util.List;

import com.foodmarket.cho.member.basket.domain.dto.BasketDTO;

public interface BasketDAO {
	void insertBasket(BasketDTO basketDTO) throws Exception;
	
	public List<String> selectGoodsId(String mb_id) throws Exception;
	
	public String selectFilePath(String goods_uid) throws Exception;
	
	public List<BasketDTO> selectBasket(String mb_id) throws Exception;
	
	public void deleteBasket(String basket_uid) throws Exception;
	
	public int selectGoods(BasketDTO basketDTO) throws Exception;
	
	public void updateGoodsQuantity(BasketDTO basketDTO) throws Exception;
	
	public int selectOrderCntMember(String mb_id) throws Exception;
}
