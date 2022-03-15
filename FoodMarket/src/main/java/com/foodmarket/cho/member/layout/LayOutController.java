package com.foodmarket.cho.member.layout;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LayOutController {
	
	@PostMapping("/memberAuth")
	@ResponseBody
	public void auth(Model model) throws Exception{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> auth = authentication.getAuthorities();
		Object authority = auth;

		model.addAttribute("auth", authority);
	}
}
