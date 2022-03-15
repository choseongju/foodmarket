package com.foodmarket.cho.member.basket.domain.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.foodmarket.cho.member.basket.domain.dao.BasketDAO;
import com.foodmarket.cho.member.basket.domain.dto.BasketDTO;

@Repository
public class BasketDAOImpl implements BasketDAO{
	
	private BasketDAO mapper;
	
	@Autowired
	public BasketDAOImpl(SqlSession sqlSession) {
		mapper = sqlSession.getMapper(BasketDAO.class);
	}
	
	@Override
	public void insertBasket(BasketDTO basketDTO) throws Exception {
		mapper.insertBasket(basketDTO);
		
	}

	@Override
	public List<String> selectGoodsId(String mb_id) throws Exception {
		return mapper.selectGoodsId(mb_id);
	}

	@Override
	public String selectFilePath(String goods_uid) throws Exception {
		return mapper.selectFilePath(goods_uid);
	}

	@Override
	public List<BasketDTO> selectBasket(String mb_id) throws Exception {
		return mapper.selectBasket(mb_id);
	}

	@Override
	public void deleteBasket(String basket_uid) throws Exception {
		mapper.deleteBasket(basket_uid);
		
	}

	@Override
	public int selectGoods(BasketDTO basketDTO) throws Exception {
		return mapper.selectGoods(basketDTO);
	}

	@Override
	public void updateGoodsQuantity(BasketDTO basketDTO) throws Exception {
		mapper.updateGoodsQuantity(basketDTO);
		
	}

	@Override
	public int selectOrderCntMember(String mb_id) throws Exception {
		return mapper.selectOrderCntMember(mb_id);
	}

}
