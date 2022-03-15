package com.foodmarket.cho.member.myPage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.foodmarket.cho.member.myPage.service.MyPageService;
import com.foodmarket.cho.security.domain.UserDTO;

@Controller
public class MyPageController {
	
	@Autowired
	MyPageService service;
	
	@GetMapping("/myPage")
	public String myPage(Authentication authentication, Model model) throws Exception{
		
		String mb_id = authentication.getName();
		
		List<UserDTO> memberList = service.memberInfo(mb_id);
		System.out.println(memberList);
		model.addAttribute("memberList", memberList);
		return "user/myPage";                               
	}
}
