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

import com.codingbamboo.miniproject.common.SearchVO;
import com.codingbamboo.miniproject.material.dto.MaterialDTO;
import com.codingbamboo.miniproject.material.service.MaterialService;
import com.google.gson.Gson;

@Controller
public class MaterialController {

	@Autowired
	MaterialService materialService;

	@RequestMapping(value = "/materialCalculation", method = RequestMethod.GET)
	public String materialCalculation(Model model) {

		List<MaterialDTO> materialList = materialService.selectAllMaterial("");

		model.addAttribute("materialList", materialList);

		return "material/materialCalculationView";
	}

	@ResponseBody
	@RequestMapping(value = "/materialSearch", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public String materialSearch(String word) {
		Gson gson = new Gson();
		String result = gson.toJson(materialService.selectAllMaterial(word));

		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/getMaterial", method = RequestMethod.POST)
	public MaterialDTO getMaterial(int meNo) {
		return materialService.selectMaterial(meNo);
	}

	// 관리자 기능
//	@ResponseBody
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

	@PostMapping("/deleteMaterial")
	public String deleteMaterial(int no, HttpServletRequest request) {

		System.out.println("/deleteMaterial 의 no=" + no);

//		materialService.deleteMaterial(no);

		request.setAttribute("msg", "삭제가 완료되었습니다.");
		request.setAttribute("url", "/adminView");
		return "alert";
	}

	@PostMapping("/updateMaterial")
	public String updateMaterial(int no, HttpServletRequest request) {

		System.out.println("/updateMaterial 의 no=" + no);

		materialService.updateMaterial(no);

		request.setAttribute("msg", "수정이 완료되었습니다.");
		request.setAttribute("url", "/adminView");
		return "alert";
	}
}