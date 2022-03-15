package com.foodmarket.cho.owner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodmarket.cho.owner.domain.CategoryDAO;
import com.foodmarket.cho.owner.domain.CategoryDTO;
import com.foodmarket.cho.owner.domain.GoodsDTO;
import com.foodmarket.cho.owner.domain.KindDTO;

@Service
public class CategoryService {

	@Autowired
	CategoryDAO dao;
	
	public List<CategoryDTO> category(){
		return dao.category();
	}
	
	public List<KindDTO> kind(String category){
		return dao.kind(category);
	}
	
	public int goodsEnrollment(GoodsDTO goodsDTO) {
		int cnt = dao.goodsEnrollment(goodsDTO);
		dao.goodsCount(goodsDTO.getGoods_name());
		return cnt;
	}
}
