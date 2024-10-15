package com.codingbamboo.miniproject.admin.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codingbamboo.miniproject.admin.dto.MaterialDTO;
import com.codingbamboo.miniproject.admin.service.MaterialService;
import com.codingbamboo.miniproject.common.SearchVO;

@Controller
public class MaterialController {
	
	@Autowired
	MaterialService materialService;
	
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
