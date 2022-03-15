package com.foodmarket.cho.member.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodmarket.cho.member.basket.domain.dto.BasketDTO;
import com.foodmarket.cho.member.order.domain.dao.OrderDAO;
import com.foodmarket.cho.security.domain.UserDTO;

@Service
public class OrderService {

	@Autowired
	OrderDAO dao;
	
	public void updateOrderCnt(BasketDTO basketDTO) throws Exception {
		dao.updateOrderCnt(basketDTO);
	}
	
	public void updateOrderCntReset(String mb_id) throws Exception{
		dao.updateOrderCntReset(mb_id);
	}
	
	public List<BasketDTO> orderGoods(String mb_id) throws Exception{
		return dao.orderGoods(mb_id);
	}
	
	public List<UserDTO> selectMemberAddress(String mb_id) throws Exception{
		return dao.selectMemberAddress(mb_id);
	}
}
