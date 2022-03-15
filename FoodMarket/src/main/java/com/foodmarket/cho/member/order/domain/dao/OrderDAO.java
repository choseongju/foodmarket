package com.foodmarket.cho.member.order.domain.dao;

import java.util.List;

import com.foodmarket.cho.member.basket.domain.dto.BasketDTO;
import com.foodmarket.cho.security.domain.UserDTO;

public interface OrderDAO {
	
	public void updateOrderCnt(BasketDTO basketDTO) throws Exception;
	
	public void updateOrderCntReset(String mb_id) throws Exception;
	
	public List<BasketDTO> orderGoods(String mb_id) throws Exception;
	
	public List<UserDTO> selectMemberAddress(String mb_id) throws Exception;
}
