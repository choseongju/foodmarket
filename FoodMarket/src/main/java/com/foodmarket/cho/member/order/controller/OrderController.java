package com.foodmarket.cho.member.order.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.foodmarket.cho.member.basket.domain.dto.BasketDTO;
import com.foodmarket.cho.member.order.service.OrderService;
import com.foodmarket.cho.security.domain.UserDTO;

@Controller
public class OrderController {

	@Autowired
	OrderService service;
	
	//주문하기를 누르면 이동
	@PostMapping("/order")
	public void order(@RequestParam("orderArr[]") List<String> list, Authentication authentication) throws Exception{
		
		String mb_id = authentication.getName();
		String basket_uid = null;
			
			for(int i=0; i<list.size(); i++) {
				BasketDTO basketDTO = new BasketDTO();
				basket_uid = list.get(i);
				
				basketDTO.setBasket_uid(basket_uid);
				basketDTO.setMb_id(mb_id);
				
				service.updateOrderCnt(basketDTO);
				System.out.println("000000");
			}
		}
	
	//구매 창
	@GetMapping("/orderForm")
	public String orderForm(Model model, Authentication authentication) throws Exception{
		String mb_id = authentication.getName();
		
		// 장바구니에서 order_cnt가 1인 상품들 조회
		List<BasketDTO> list = service.orderGoods(mb_id);
		model.addAttribute("list", list);
		
		List<Integer> resultList = new ArrayList<>();
		
		for(int i=0; i<list.size(); i++) {
			int price = Integer.parseInt(list.get(i).getGoods_price());
			int quantity = Integer.parseInt(list.get(i).getGoods_select_quantity());
			
			int result = price * quantity;
			
			resultList.add(result);
		}
		
		int resultPrice = 0;
		
		for(int i=0; i<resultList.size(); i++) {
			 resultPrice += resultList.get(i);
		}
		
		if(resultPrice >= 50000) {
			model.addAttribute("deliveryPrice", 0);
		}else {
			model.addAttribute("deliveryPrice", 3000);
		}
		
		model.addAttribute("resultPrice", resultPrice);
		
		// 현재 로그인된 사용자 주소 조회
		List<UserDTO> addressList = service.selectMemberAddress(mb_id);
		
		String name = addressList.get(0).getName();
		String phone = addressList.get(0).getPhone();
		String address = addressList.get(0).getAddress();
		String address_detail = addressList.get(0).getAddress_detail();

		model.addAttribute("address", address);
		model.addAttribute("address_detail", address_detail);
		model.addAttribute("name", name);
		model.addAttribute("phone", phone);
		
		
		return "user/order";
	}
	
	//장바구니에서 구매창으로 이동안하고 취소했을 시 
	@PostMapping("/orderCntReset") 
	public void OrderCntReset(Authentication authentication) throws Exception{
		String mb_id = authentication.getName();
		service.updateOrderCntReset(mb_id);
	}
}
