package com.foodmarket.cho.member.category.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodmarket.cho.member.category.domain.dao.ByCategoryDAO;
import com.foodmarket.cho.owner.domain.GoodsDTO;

@Service
public class ByCategoryService {

	@Autowired
	ByCategoryDAO dao;
	
	public List<GoodsDTO> vegetable() throws Exception{
		return dao.vegetable();
	}
}
