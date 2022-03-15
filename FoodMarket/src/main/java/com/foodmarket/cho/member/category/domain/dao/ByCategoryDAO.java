package com.foodmarket.cho.member.category.domain.dao;

import java.util.List;

import com.foodmarket.cho.owner.domain.GoodsDTO;

public interface ByCategoryDAO {
	
	public List<GoodsDTO> vegetable() throws Exception;
}
