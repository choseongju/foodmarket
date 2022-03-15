package com.foodmarket.cho.member.basket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foodmarket.cho.member.basket.domain.dto.BasketDTO;
import com.foodmarket.cho.member.basket.service.BasketService;

import net.sf.json.JSONArray;


@Controller
public class BasketController {
	
	@Autowired
	BasketService service;
	
	@PostMapping("user/basket")
	@ResponseBody
	public void basket (@RequestParam("id") String id,
							 @RequestParam("name") String name,
							 @RequestParam("price") String price,
							 @RequestParam("filePath") String filePath,
							 @RequestParam("quantity") String quantity) throws Exception{

		//ajax로 장바구니 누르면 바로 db에 값 저장하고 뿌려주는 form보여주기
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String mb_id = authentication.getName();
		System.out.println(mb_id);
		
		//장바구니에 해당 사용자 id와 상품이 있는지를 검색하기 위해 담는 DTO
		BasketDTO selectGoodsDTO = new BasketDTO();
		
		//장바구니에 insert하기위한 DTO
		BasketDTO basketDTO = new BasketDTO();
		
		//사용자 id와 상품 id를 set하고
		selectGoodsDTO.setMb_id(mb_id);
		selectGoodsDTO.setGoods_uid(id);
		
		// 사용자와 상품이 있는지 count 조회를 한 후 
		int a = service.selectGoods(selectGoodsDTO);
		
		//만약 조회값이 0이면 dto set을 하고 insert
		if(a == 0) {
			basketDTO.setGoods_uid(id);
			basketDTO.setGoods_name(name);
			basketDTO.setGoods_select_quantity(quantity);
			basketDTO.setGoods_price(price);
			basketDTO.setGoods_filePath(filePath);
			basketDTO.setMb_id(mb_id);
			
			service.insertBakset(basketDTO);
			
		}else {
			//없을경우 id와 선택 수량만 가져와 update
			basketDTO.setGoods_uid(id);
			basketDTO.setGoods_select_quantity(quantity);
			
			service.updateGoodsQuantity(basketDTO);
		}
	}
	
	@GetMapping("/basketForm")
	public String basketForm(Model model) throws Exception{
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String mb_id = authentication.getName();
		
		int cnt = service.selectOrderCntMember(mb_id);
		model.addAttribute("cnt", cnt);
		
		//장바구니 list 받아오기 
		List<BasketDTO> basketList = service.selectBasket(mb_id);
		
		model.addAttribute("basketList", JSONArray.fromObject(basketList));
		
		
		return "user/basket";
	}
	
	@PostMapping("/deleteBasket")
	@ResponseBody
	public void deleteBasket(@RequestParam("uid") String basket_uid) throws Exception{
		service.deleteBasket(basket_uid);
	}
}
