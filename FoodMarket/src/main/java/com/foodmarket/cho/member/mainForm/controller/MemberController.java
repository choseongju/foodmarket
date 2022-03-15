package com.foodmarket.cho.member.mainForm.controller;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foodmarket.cho.member.mainForm.service.MainFormService;
import com.foodmarket.cho.owner.domain.GoodsDTO;
import com.foodmarket.cho.security.domain.UserDTO;
import com.foodmarket.cho.security.service.UserDetailService;

import net.sf.json.JSONArray;

@Controller
public class MemberController {

	@Autowired
	UserDetailService userService;

	@Autowired
	MainFormService service;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/")
	public String sayHello(Model model, HttpServletRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName();

		if (userId != null && userId != "anonymousUser") {
			String userName = userService.selectUserName(userId);
			model.addAttribute("userName", userName);
		}

		// 권한 검색하여 model에 담기
		Collection<? extends GrantedAuthority> auth = authentication.getAuthorities();
		System.out.println(auth);
		model.addAttribute("auth", JSONArray.fromObject(auth));

		List<GoodsDTO> list = service.selectGoods();

		// 메인 페이지 랜덤하게 4개 페이지에 띄우기 위해 randomList 선언
		List<GoodsDTO> RandomList = new ArrayList<>();

		// random 선언
		Random random = new Random();
		// 4개만 출력하기 때문에 4까지 form돌리고 randomList에 add하고 add한 value는 기존 list에서 삭제
		for (int i = 0; i < 4; i++) {
			int randomValue = random.nextInt(list.size());
			RandomList.add(list.get(randomValue));
			list.remove(randomValue);
		}

		model.addAttribute("goodsList", RandomList);

		// 조회수 top4 uid만 검색
		List<String> top4list = service.viewCountTop4Goods();

		// uid로 검색하고
		List<GoodsDTO> selectTop4List = new ArrayList<>();

		// 검색해서 받아온 list를 list에 추가
		List<GoodsDTO> mainTop4List = new ArrayList<>();

		for (int i = 0; i < 4; i++) {
			String uid = top4list.get(i);
			selectTop4List = service.top4Goods(uid);
			mainTop4List.addAll(i, selectTop4List);
		}

		model.addAttribute("top4List", mainTop4List);

		// 좋아요 수 TOP4 상품 조회
		List<String> likeTop4List = service.likeCountTop4Goods();

		// likeTop4List에 uid로 해당 상품 정보 검색하고
		List<GoodsDTO> selectLikeTop4List = new ArrayList<>();

		// mainLikeTop4List에 검색한 상품 정보 리스트 전부 다 담기
		List<GoodsDTO> mainLikeTop4List = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			String uid = likeTop4List.get(i);
			selectLikeTop4List = service.likeTop4Goods(uid);
			mainLikeTop4List.addAll(i, selectLikeTop4List);
		}

		model.addAttribute("likeTop4List", mainLikeTop4List);

		return "user/userMain";
	}

	// Spring Security(이하 '시큐리티') 가 적용되면
	// /login 등의 url 로의 request 를 시큐리티가 모두 낚아 챕니다.
	// ※ 나중에 SecurityConfig 가 설정되면 낚아 채지 않게 된다.
	@GetMapping("/login")
	public String login(HttpServletRequest request, Model model) {
		// 로그인 직전의 url 을 Session 에 기록
		String referer = request.getHeader("Referer");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> auth = authentication.getAuthorities();
		model.addAttribute("auth", JSONArray.fromObject(auth));
		if (referer != null)
			request.getSession().setAttribute("url_prior_login", referer);

		return "user/loginForm";
	}

	@PostMapping("/login")
	public String loginFail() {
		System.out.println("POST: /login");
		return "user/loginForm";
	}

	// 현재로그인한 정보 Authentication 보기
	@RequestMapping("/auth")
	@ResponseBody
	public Authentication auth(HttpSession session) {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	@GetMapping("/join")
	public String join() {
		return "user/joinForm";
	}

	@PostMapping("/joinOk")
	public String joinOk(UserDTO user) {
		System.out.println("/joinOk: " + user);

		// password 는 암호화 하여 저장
		String rawPassword = user.getPw();
		String encPassword = passwordEncoder.encode(rawPassword);
		user.setPw(encPassword);

		@SuppressWarnings("unused")
		int cnt = userService.addMember(user);

		return "redirect:/";
	}

	@RequestMapping("/accessError")
	public void accessError() {
	}

	@PostMapping("/checkID")
	@ResponseBody
	public int checkID(@RequestParam("id") String id) {

		int cnt = userService.checkID(id);

		return cnt;
	}

	@PostMapping("/checkEmail")
	@ResponseBody
	public int checkEmail(@RequestParam("email") String email) {

		int cnt = userService.checkEmail(email);
		return cnt;
	}

	@GetMapping("/inquiry")
	public String inquiry() throws IOException {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication);
		return "user/inquiry";
	}

	@GetMapping("/search")
	public String search(@RequestParam("keywordText") String keyword, Model model) throws Exception {
		

		 System.out.println(keyword);
		 
		 List<GoodsDTO> goodsList = service.goodsSearch(keyword);
		 model.addAttribute("goodsSearchList", goodsList);
		  
		 // 권한 검색하여 model에 담기 Authentication authentication =
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); Collection<? extends
		 GrantedAuthority> auth = authentication.getAuthorities();
		 model.addAttribute("auth", JSONArray.fromObject(auth));
		 
		model.addAttribute("keyword", keyword);

		return "user/goodsSearch";
	}

}