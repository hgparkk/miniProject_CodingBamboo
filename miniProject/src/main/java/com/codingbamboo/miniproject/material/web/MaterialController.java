package com.codingbamboo.miniproject.material.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codingbamboo.miniproject.material.dto.MaterialDTO;
import com.codingbamboo.miniproject.material.service.MaterialService;
import com.google.gson.Gson;

@Controller
public class MaterialController {

	@Autowired
	MaterialService materialService;

	// 자재별 탄소 배출량 계산 페이지 이동
	@RequestMapping(value = "/materialCalculation", method = RequestMethod.GET)
	public String materialCalculation(Model model) {

		// 건축자재 모두 불러오기
		List<MaterialDTO> materialList = materialService.selectAllMaterial("");

		model.addAttribute("materialList", materialList);

		return "material/materialCalculationView";
	}

	// 자재 검색 결과 보내기
	@ResponseBody
	@RequestMapping(value = "/materialSearch", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public String materialSearch(String word) {
		Gson gson = new Gson();
		String result = gson.toJson(materialService.selectAllMaterial(word));

		return result;
	}

	// 자재의 탄소 배출량 불러오기
	@ResponseBody
	@RequestMapping(value = "/getMaterial", method = RequestMethod.POST)
	public MaterialDTO getMaterial(int meNo) {
		return materialService.selectMaterial(meNo);
	}

	// 관리자 기능
	
	// 자재 관리 페이지 이동
	@RequestMapping("/adminView")
	public String adminView(Model model, String searchWord) {
		List<MaterialDTO> materialList = new ArrayList<>();

		if (searchWord == null || searchWord.isEmpty()) {
			materialList = materialService.getMaterialList("");
		} else {
			materialList = materialService.getMaterialList(searchWord);
		}
		model.addAttribute("keyMaterialList", materialList);

		return "admin/adminView";

	}

	// 자재 삭제
	@PostMapping("/deleteMaterial")
	public String deleteMaterial(HttpServletRequest request, int meNo) {

		materialService.deleteMaterial(meNo);

		request.setAttribute("msg", "삭제가 완료되었습니다.");
		request.setAttribute("url", "/adminView");
		return "alert";
	}

	// 자재 수정
	@PostMapping("/updateMaterial")
	public String updateMaterial(HttpServletRequest request, MaterialDTO material) {

		materialService.updateMaterial(material);

		request.setAttribute("msg", "수정이 완료되었습니다.");
		request.setAttribute("url", "/adminView");
		return "alert";
	}
	
	// 자재 삽입
	@PostMapping("/insertMaterial")
	public String insertMaterial(HttpServletRequest request, MaterialDTO material) {
		
		materialService.insertMaterial(material);
		
		request.setAttribute("msg", "자재가 추가되었습니다.");
		request.setAttribute("url", "/adminView");
		return "alert";
	}
	
	
}