package com.foodmarket.cho.member.like.controller;

import java.util.ArrayList;
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

import com.foodmarket.cho.member.like.service.LikeService;
import com.foodmarket.cho.owner.domain.GoodsDTO;

import net.sf.json.JSONArray;

@Controller
public class LikeController {
	
	@Autowired
	LikeService service;
	
	@GetMapping("/likeForm")
	public String likeForm(Model model) throws Exception{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String mb_id = authentication.getName();
		
		List<String> list = service.selectGoodsUid(mb_id);
		
		List<GoodsDTO> goodsList = new ArrayList<>();
		for(int i=0; i<list.size(); i++) {
			String goods_uid = list.get(i);
			goodsList.addAll(service.selectGoods(goods_uid));
		}

		model.addAttribute("goodsList", JSONArray.fromObject(goodsList));
		return "user/like";
	}
	
	/*
	 * @PostMapping("/like")
	 * 
	 * @ResponseBody public void like(Model model) throws Exception{
	 * 
	 * Authentication authentication =
	 * SecurityContextHolder.getContext().getAuthentication(); String mb_id =
	 * authentication.getName();
	 * 
	 * List<String> list = service.selectGoodsUid(mb_id);
	 * 
	 * model.addAttribute("lista", JSONArray.fromObject(list));
	 * System.out.println(list); }
	 */
	
	@PostMapping("/deleteLike")
	@ResponseBody
	public void deleteLike(@RequestParam("uid") String goods_uid) throws Exception{
		service.deleteLike(goods_uid);
	}
}
