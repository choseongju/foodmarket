package com.foodmarket.cho.member.order.domain.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.foodmarket.cho.member.basket.domain.dto.BasketDTO;
import com.foodmarket.cho.member.order.domain.dao.OrderDAO;
import com.foodmarket.cho.security.domain.UserDTO;

@Repository
public class OrderDAOImpl implements OrderDAO{

	private OrderDAO mapper;
	
	@Autowired
	public OrderDAOImpl(SqlSession sqlSession) {
		mapper = sqlSession.getMapper(OrderDAO.class);
	}

	@Override
	public void updateOrderCnt(BasketDTO basketDTO) throws Exception {
		mapper.updateOrderCnt(basketDTO);
	}

	@Override
	public void updateOrderCntReset(String mb_id) throws Exception {
		mapper.updateOrderCntReset(mb_id);
		
	}

	@Override
	public List<BasketDTO> orderGoods(String mb_id) throws Exception {
		return mapper.orderGoods(mb_id);
	}

	@Override
	public List<UserDTO> selectMemberAddress(String mb_id) throws Exception {
		return mapper.selectMemberAddress(mb_id);
	}
	
	
}
