package com.foodmarket.cho.owner.controller;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import org.springframework.web.multipart.MultipartFile;

import com.foodmarket.cho.owner.domain.CategoryDTO;
import com.foodmarket.cho.owner.domain.GoodsDTO;
import com.foodmarket.cho.owner.domain.KindDTO;
import com.foodmarket.cho.owner.service.CategoryService;

import net.sf.json.JSONArray;

@Controller
public class OwnerController {

	@Autowired
	CategoryService service;
	
	@GetMapping("/owner/index")
	public String index() throws Exception{
		return "owner/ownerMain";
	}
	
	// 상품 등록 페이지
	@GetMapping("/owner/goodsEnrollment")
	public String goodsEnrollment(Model model) throws Exception{
		
		List<CategoryDTO> categoryList = new ArrayList<>();
		categoryList = service.category();
	
		JSONArray jsonCategoryArray = new JSONArray();
		jsonCategoryArray = JSONArray.fromObject(categoryList);
		
		model.addAttribute("categoryList",jsonCategoryArray);
		
		return "owner/goodsEnrollment";
	}
	
	//카테고리 선택 후 카테고리 별 종류 출력
	@PostMapping("/owner/selectCategory")
	@ResponseBody
	public JSONArray selectCategory(@RequestParam("category") String category) throws Exception{
		
		List<KindDTO> kindList = new ArrayList<>();
		kindList = service.kind(category);
		
		JSONArray jsonKindArray = new JSONArray();
		jsonKindArray = JSONArray.fromObject(kindList);
		
		return jsonKindArray;
	}
	
	@PostMapping("/owner/enrollment")
	@ResponseBody
	public void enrollment(GoodsDTO goodsDTO, @RequestParam("file") MultipartFile file) throws Exception{
		
		LocalDateTime now = LocalDateTime.now();
		String formatDateNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"));
		
		//파일 이름
		String originalFileName = file.getOriginalFilename();
		String fileType = originalFileName.substring(originalFileName.indexOf("."));
		String path = "C:\\develp\\image\\" + formatDateNow + ".";
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String id = authentication.getName();
		
		String filePath = path + id + fileType;
		//일단은 한글명 이미지 파일 한글 자꾸 깨져서 임시조치로 id명 || 추후에 바꿀 예정
		String fileName = formatDateNow + "." + id + fileType;
		//파일 업로드
		file.transferTo(new File(filePath));
		
		goodsDTO.setGoods_filePath(fileName);
		
		service.goodsEnrollment(goodsDTO);
	}
	
}
