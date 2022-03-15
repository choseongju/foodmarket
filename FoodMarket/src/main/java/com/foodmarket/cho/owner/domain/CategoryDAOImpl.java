package com.foodmarket.cho.owner.domain;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDAOImpl implements CategoryDAO{
	
	private CategoryDAO mapper;
	
	@Autowired
	public CategoryDAOImpl(SqlSession sqlSession) {
		mapper = sqlSession.getMapper(CategoryDAO.class);
	}
	
	@Override
	public List<CategoryDTO> category() {
		return mapper.category();
	}

	@Override
	public List<KindDTO> kind(String category) {
		return mapper.kind(category);
	}

	@Override
	public int goodsEnrollment(GoodsDTO goodsDTO) {
		return mapper.goodsEnrollment(goodsDTO);
	}

	@Override
	public int goodsCount(String goods_name) {
		return mapper.goodsCount(goods_name);
	}

}
