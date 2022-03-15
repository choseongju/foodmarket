package com.foodmarket.cho.owner.domain;

import java.util.List;

public interface CategoryDAO {

	public List<CategoryDTO> category();
	
	public List<KindDTO> kind(String category);
	
	public int goodsEnrollment(GoodsDTO goodsDTO);
	
	public int goodsCount(String goods_name);
}
