package com.foodmarket.cho.member.category.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.foodmarket.cho.member.category.service.ByCategoryService;
import com.foodmarket.cho.owner.domain.GoodsDTO;

@Controller
public class CategoryController {

	@Autowired
	ByCategoryService service;
	
	@GetMapping("/vegatables")
	public String vegatables(Model model) throws Exception{
		
		List<GoodsDTO> vegatablesList = service.vegetable();
		
		model.addAttribute("vegatablesList",vegatablesList);
		
		return "user/category/vegetable";
	}
	
	@GetMapping("/fisheries")
	public String fisheries() throws Exception{
		return "user/category/fisheries";
	}
	
	@GetMapping("/meat")
	public String meat() throws Exception{
		return "user/category/meat";
	}
	
	@GetMapping("/salad")
	public String salad() throws Exception{
		return "user/category/salad";
	}
	
	@GetMapping("/drink")
	public String drink() throws Exception{
		return "user/category/drink";
	}
	
	@GetMapping("/snack")
	public String snack() throws Exception{
		return "user/category/snack";
	}
	
	@GetMapping("/cosmetic")
	public String cosmetic() throws Exception{
		return "user/category/cosmetic";
	}
	
	@GetMapping("/bakery")
	public String bakery() throws Exception{
		return "user/category/bakery";
	}
	
	@GetMapping("fruit")
	public String fruit() throws Exception{
		return "user/category/fruit";
	}
}
