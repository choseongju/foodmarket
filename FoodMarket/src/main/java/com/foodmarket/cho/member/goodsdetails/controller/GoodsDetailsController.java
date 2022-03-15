package com.foodmarket.cho.member.goodsdetails.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foodmarket.cho.member.goodsdetails.service.GoodsDetailsService;
import com.foodmarket.cho.owner.domain.GoodsDTO;

import net.sf.json.JSONArray;

@Controller
public class GoodsDetailsController {
	
	@Autowired
	GoodsDetailsService service;
	
	// 상품 상세조회
	@GetMapping("/goodsDetails")
	public String goodsDetailsForm(@RequestParam("goods_uid") String goods_uid, Model model) throws Exception{
		
		//uid값으로 상세조회
		List<GoodsDTO> goodsList = service.goodsDetails(goods_uid);
		model.addAttribute("JSONGoodsList", JSONArray.fromObject(goodsList));
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		//현재 접속된 사용자의 아이디
		String id = authentication.getName();
		
		//현재 사용자의 권한 가져오기
		//로그인하지 않은 사용자는 anonymousUser 한 사람은 각 권한별로 출력
		Collection<? extends GrantedAuthority> auth = authentication.getAuthorities();
		model.addAttribute("auth",JSONArray.fromObject(auth));
		
		//조회수 update
		service.updateViewCount(goods_uid);
		
		//사용자가 현재 상품 좋아요 체크 여부 확인
		
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("goods_uid", goods_uid);
		
		int goodsLike = service.selectGoodsLike(map);
		model.addAttribute("goodsLike",goodsLike);
		return "user/goodsDetails";
	}
	
	//좋아요
	@PostMapping("/goodsLike")
	@ResponseBody
	public void goodsLike(@RequestParam("id") String goods_uid, @RequestParam("name") String goods_name) throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String id = authentication.getName();
		
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("goods_uid", goods_uid);
		map.put("goods_name", goods_name);
		
		System.out.println(map + "asdasd");
		service.insertGoodsLike(map);
		service.updateGoodsLike(goods_uid);
	}
	
	//좋아요 삭제
	@PostMapping("/deleteGoodsLike")
	@ResponseBody
	public void deleteGoodsList(@RequestParam("id") String goods_uid, @RequestParam("name") String goods_name) throws Exception{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String id = authentication.getName();
		
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("goods_uid", goods_uid);
		map.put("goods_name", goods_name);
		
		service.deleteGoodsLike(map);
		service.cancelGoodsLike(goods_uid);
	}
}
