package com.codingbamboo.miniproject.material.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
//	@ResponseBody
	@RequestMapping("/adminView")
	public String adminView(Model model, SearchVO search) {
		List<MaterialDTO> materialList = materialService.getMaterialList();
		
		model.addAttribute("keyMaterialList", materialList);
		
		int materialCount = materialService.getMaterialCount(search);
		
		search.setMaterialCount(materialCount);
		
		
		model.addAttribute("keySearch", search);
		
		System.out.println(search);
		
		return "admin/adminView";
		
	}
	
//	@ResponseBody
//	@PostMapping("/adminView")
//	public MaterialDTO MaterialAddDo(MaterialDTO material) { 
//		
//		
//		materialService.insertMaterial(material);
//		
//		MaterialDTO result = materialService.getMaterial();
//		System.out.println(result);
//		
//		return result;
//	}
}