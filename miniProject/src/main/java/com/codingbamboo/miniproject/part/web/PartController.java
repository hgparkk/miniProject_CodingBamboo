package com.codingbamboo.miniproject.part.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PartController {

	@RequestMapping("/partView")
	public String getEmissionsPage(Model model) {
		
		return "part/partView";
	}
	
	/*
	 * @PostMapping("/emissions/search") public String searchEmissions(
	 * 
	 * @RequestParam("yearFrom") String yearFrom,
	 * 
	 * @RequestParam("yearTo") String yearTo,
	 * 
	 * @RequestParam("mainCategory") String mainCategory,
	 * 
	 * @RequestParam("subCategory") String subCategory, Model model ) { // 검색 조건에 따른
	 * 데이터베이스에서 정보 조회 List<EmissionData> results =
	 * emissionService.getEmissions(yearFrom, yearTo, mainCategory, subCategory);
	 * model.addAttribute("results", results); return "emissions"; // 조회 결과를 다시 같은
	 * 페이지로 반환 }
	 */
}


